package com.tamnn.vnexpressfeeds.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.dependency.AppComponent
import com.tamnn.vnexpressfeeds.dependency.ComponentUtil
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory


abstract class BaseMvpActivity<V, out P : Presenter<V, S>, S : ViewState, out T : Screen> : MVPActivity<V, P, S>() {

    companion object {
        private const val KEY_SCREEN = "BaseMvpActivity_screen"
        internal const val KEY_SCREEN_CACHE = "BaseMvpActivity_screen_cache"

        @JvmStatic
        fun <T : Screen> setScreen(intent: Intent, screen: T) {
            val bundle = Bundle()
            bundle.putParcelable(KEY_SCREEN, screen)
            intent.putExtra(KEY_SCREEN, bundle)
        }
    }

    private var allowCollapse = true
    private var _Screen: T? = null
    private var _CurrentActivityStack: MutableList<String>? = null
    private var _UseCaseFactory: UseCaseFactory? = null
    private var _SchedulerFactory: SchedulerFactory? = null
    private var mBus: RxBus? = null


    val screen: T
        get() = _Screen!!

    abstract val layoutResource: Int

    protected open val landscapeSupport = false


    protected open val isTranslucent = true

    protected open fun onAppReload() {

    }

    protected open fun onAppExit() {

    }

    protected open fun onApplicationPause() {

    }

    protected open fun onApplicationResume() {

    }

    protected open fun showHideAudioView(show: Boolean, isPause: Boolean) {

    }

    open fun openAudioView(url: String?, isPlayInDetailView: Boolean, stackCount: Int){

    }


    override fun onResume() {
        super.onResume()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        _Screen = intent.getBundleExtra(KEY_SCREEN)?.getParcelable(
            KEY_SCREEN
        )
            ?: savedInstanceState?.getParcelable(KEY_SCREEN)
        if (_Screen == null) {
            val screenKeyCache = intent.getStringExtra(KEY_SCREEN_CACHE)
            if (!screenKeyCache.isNullOrEmpty()) {
                val dataCache = ComponentUtil.getComponent(applicationContext, AppComponent::class.java).dataCache
                _Screen = dataCache.popScreen(screenKeyCache) as? T?
            }
        }
        super.onCreate(savedInstanceState)
        val appComponent = ComponentUtil.getComponent(applicationContext, AppComponent::class.java)
        _CurrentActivityStack = appComponent.currentActivityStack
        _UseCaseFactory = appComponent.useCaseFactory
        _SchedulerFactory = appComponent.schedulerFactory
        mBus = appComponent.bus

        try {
            setContentView(layoutResource)
        } catch (ex: Exception) {
            finish()
            return
        }
        if (!isTranslucent || Build.VERSION.SDK_INT != 26) {
            requestedOrientation = if (landscapeSupport) ActivityInfo.SCREEN_ORIENTATION_FULL_USER else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }


    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_SCREEN, _Screen)
    }

    @SuppressLint("RestrictedApi")
    override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        super.startActivityForResult(intent, requestCode, options)
        overridePendingTransition(0, 0)
    }

    override fun startActivityFromFragment(fragment: Fragment, intent: Intent?, requestCode: Int, options: Bundle?) {
        super.startActivityFromFragment(fragment, intent, requestCode, options)
        overridePendingTransition(0, 0)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }
}