package com.tamnn.vnexpressfeeds.feature.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager

import com.tamnn.vnexpressfeeds.R
import com.tamnn.vnexpressfeeds.MyApplication
import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.BaseLinearLayoutManager
import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.Item
import com.tamnn.vnexpressfeeds.common.ErrorConsumer
import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.dependency.DataCache
import com.tamnn.vnexpressfeeds.dependency.HasComponent
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.feature.main.event.ArticleClickEvent
import com.tamnn.vnexpressfeeds.feature.web.WebActivity
import com.tamnn.vnexpressfeeds.feature.web.WebScreen
import com.tamnn.vnexpressfeeds.mvp.BaseMvpActivity

import dagger.Lazy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter, MainViewState, MainScreen>(),
    HasComponent<MainComponent>, MainContract.View, ActivityCompat.OnRequestPermissionsResultCallback  {

    companion object {

        fun instance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    @Inject
    lateinit var _SchedulerFactory: SchedulerFactory
    @Inject
    lateinit var _Bus: RxBus

    @Inject
    lateinit var _Adapter: ArticleAdapter

    override fun onCreatePresenter(context: Context) = component.presenter

    override fun onCreateViewState(context: Context): MainViewState {
        return MainViewState()
    }

    override val viewStateTag: String get() = MainViewState::class.java.name

    override val layoutResource get() = R.layout.activity_main

    private var _Disposable: CompositeDisposable? = null

    lateinit var _LayoutManager: LinearLayoutManager


    private var permissionDenied = false

    override val component by lazy {
        MyApplication.get(this).component.plus(MainModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(if(MyApplication.get(this).isNightModeEnabled()) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)

        component.inject(this)

        switchCompat?.isChecked = MyApplication.get(this).isNightModeEnabled()

        rvRssFeeds?.adapter = _Adapter
        _Adapter.setReady(true)
        _LayoutManager = BaseLinearLayoutManager(this)
        rvRssFeeds?.layoutManager = _LayoutManager
        if (intent.action == Intent.ACTION_MAIN && !isTaskRoot) {
            finish()
        }

        _Disposable = CompositeDisposable(_Adapter.event
            .throttleFirst(300L, TimeUnit.MILLISECONDS)
            .observeOn(_SchedulerFactory.main())
            .subscribe(Consumer {
                when (it) {
                    is ArticleClickEvent -> onArticleClicked(it.link)

                }
            }, ErrorConsumer()))

        switchCompat?.setOnCheckedChangeListener { compoundButton, checked ->
            saveCurrentPosition()
            MyApplication.get(this).toggleNightMode()
            AppCompatDelegate.setDefaultNightMode(if(checked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
        }

        btnRefresh.setOnRefreshListener {

            presenter.refreshRssFeeds()
        }
    }

    private fun saveCurrentPosition(){
        val firstItem = _LayoutManager.findFirstVisibleItemPosition()
        val firstItemView: View = _LayoutManager.findViewByPosition(firstItem) ?: return
        val topOffset = firstItemView.top
        presenter.currentPosition = firstItem
        presenter.topOffset = topOffset
    }

    private fun onArticleClicked(link: String){

        val screen = WebScreen(url = link)
        val intent = WebActivity.instance(this, screen)
        startActivity(intent)
    }

    override fun showItems(items: List<Item>) {
        _Adapter.updateItems(items)
        rvRssFeeds?.post {
            _LayoutManager.scrollToPositionWithOffset(presenter.currentPosition, presenter.topOffset)
        }
        btnRefresh.isRefreshing = false

    }

    override fun onDestroy() {
        _Disposable?.dispose()
        _Adapter.onDestroy()

        super.onDestroy()
    }
}