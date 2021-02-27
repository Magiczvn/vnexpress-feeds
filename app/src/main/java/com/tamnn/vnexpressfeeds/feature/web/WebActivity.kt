package com.tamnn.vnexpressfeeds.feature.web

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.tamnn.vnexpressfeeds.MyApplication
import com.tamnn.vnexpressfeeds.R
import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.dependency.HasComponent
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.feature.webtab.WebTabFragment
import com.tamnn.vnexpressfeeds.feature.webtab.WebTabScreen
import com.tamnn.vnexpressfeeds.mvp.BaseMvpActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class WebActivity : BaseMvpActivity<WebContract.View, WebContract.Presenter, WebViewState, WebScreen>(),
        HasComponent<WebComponent>, WebContract.View {
    companion object {

        fun instance(context: Context, screen: WebScreen): Intent {
            if (isWebViewExists()) {
                val intent = Intent(context, WebActivity::class.java)
                setScreen(intent, screen)
                return intent
            } else {
                return Intent(Intent.ACTION_VIEW, Uri.parse(screen.url))
            }
        }

        private fun isWebViewExists(): Boolean {
            try {
                Class.forName("android.webkit.WebView")
                return true
            } catch (ex: ClassNotFoundException) {
            }

            return false
        }
    }

    //region Dependency
    @Inject
    lateinit var _SchedulerFactory: SchedulerFactory
    @Inject
    lateinit var _Bus: RxBus

    //endregion Dependency

    //region Private
    private var _Disposable: CompositeDisposable? = null

    private var _IsActivityTranslucent = true
    //endregion Private

    var _WebTabFragment: WebTabFragment? = null

    var systemWindowInsetTop: Int = 0

    //region MVP
    override fun onCreatePresenter(context: Context) = component.presenter

    override fun onCreateViewState(context: Context) = WebViewState()

    override val viewStateTag: String get() = WebViewState::class.java.name

    override val layoutResource get() = R.layout.web_activity

    //endregion MVP

    //region HasComponent
    override val component by lazy {
        MyApplication.get(this).component.plus(WebModule(this))
    }
    //endregion HasComponent

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)

        _Disposable = CompositeDisposable()

        if (supportFragmentManager.findFragmentById(R.id.web_fl_fragment) == null) {
            val webTabScreen = WebTabScreen(url = screen.url)
            val fragment = WebTabFragment.instance(screen = webTabScreen)
            _WebTabFragment = fragment
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.web_fl_fragment, fragment)
            transaction.commitAllowingStateLoss()
        }

    }


    override fun onDestroy() {
        _Disposable?.dispose()

        super.onDestroy()
    }


    //endregion Handle Click
}