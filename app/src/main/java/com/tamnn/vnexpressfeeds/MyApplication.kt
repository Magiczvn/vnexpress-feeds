package com.tamnn.vnexpressfeeds

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.dependency.AppComponent
import com.tamnn.vnexpressfeeds.dependency.AppModule
import com.tamnn.vnexpressfeeds.dependency.DaggerAppComponent
import com.tamnn.vnexpressfeeds.dependency.HasComponent


open class MyApplication : Application(), HasComponent<AppComponent>  {

    private lateinit var _AppComponent: AppComponent

    private var _ActivityLifecycle: ActivityLifecycle? = null

    companion object {
        fun get(context: Context): MyApplication = context.applicationContext as MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        _AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }



    override fun onTerminate() {
        super.onTerminate()
    }

    override val component: AppComponent
        get() = _AppComponent


    inner class ActivityLifecycle(bus: RxBus) : ActivityLifecycleCallbacks {

        private val _Bus = bus


        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

        }

        override fun onActivityStarted(activity: Activity) {


        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {


        }

        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {

        }
    }

}