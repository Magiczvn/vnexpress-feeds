package com.tamnn.vnexpressfeeds.feature.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.tamnn.vnexpressfeeds.R
import com.tamnn.vnexpressfeeds.MyApplication
import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.dependency.DataCache
import com.tamnn.vnexpressfeeds.dependency.HasComponent
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.mvp.BaseMvpActivity

import dagger.Lazy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter, MainViewState, MainScreen>(),
    HasComponent<MainComponent>, MainContract.View, ActivityCompat.OnRequestPermissionsResultCallback  {

    companion object {

        fun instance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }

        private const val LOCATION_PERMISSION_REQUEST_CODE = 1

    }

    @Inject
    lateinit var _SchedulerFactory: SchedulerFactory
    @Inject
    lateinit var _Bus: RxBus
    @Inject
    lateinit var _DataCache: Lazy<DataCache>

    override fun onCreatePresenter(context: Context) = component.presenter

    override fun onCreateViewState(context: Context): MainViewState {
        return MainViewState()
    }

    override val viewStateTag: String get() = MainViewState::class.java.name

    override val layoutResource get() = R.layout.activity_main

    private var _Disposable: CompositeDisposable? = null


    private var permissionDenied = false

    override val component by lazy {
        MyApplication.get(this).component.plus(MainModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        if (intent.action == Intent.ACTION_MAIN && !isTaskRoot) {
            finish()
        }

        _Disposable = CompositeDisposable()
    }

    override fun onDestroy() {
        _Disposable?.dispose()
        super.onDestroy()
    }
}