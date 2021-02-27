package com.tamnn.vnexpressfeeds.dependency

import android.app.Application
import android.telephony.TelephonyManager


import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import com.tamnn.vnexpressfeeds.feature.main.MainComponent
import com.tamnn.vnexpressfeeds.feature.main.MainModule
import com.tamnn.vnexpressfeeds.feature.web.WebComponent
import com.tamnn.vnexpressfeeds.feature.web.WebModule
import com.tamnn.vnexpressfeeds.feature.webtab.WebTabComponent
import com.tamnn.vnexpressfeeds.feature.webtab.WebTabModule
import dagger.Component
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RssFeedModule::class])
interface AppComponent {

    val application: Application
    val bus: RxBus

    operator fun plus(mainModule: MainModule): MainComponent

    operator fun plus(webModule: WebModule): WebComponent

    operator fun plus(webTabModule: WebTabModule): WebTabComponent

    val useCaseFactory: UseCaseFactory
    val schedulerFactory: SchedulerFactory
    val dataCache: DataCache

    @get:Type("activity_stack")
    val activityStack: MutableList<String>

    @get:Type("current_activity_stack")
    val currentActivityStack: MutableList<String>
}
