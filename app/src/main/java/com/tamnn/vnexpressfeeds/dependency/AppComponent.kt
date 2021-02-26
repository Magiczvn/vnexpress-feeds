package com.tamnn.vnexpressfeeds.dependency

import android.app.Application
import android.telephony.TelephonyManager


import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import com.tamnn.vnexpressfeeds.feature.main.MainComponent
import com.tamnn.vnexpressfeeds.feature.main.MainModule
import dagger.Component
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RssFeedModule::class])
interface AppComponent {

    val application: Application
    val bus: RxBus

    operator fun plus(mainModule: MainModule): MainComponent

    val useCaseFactory: UseCaseFactory
    val schedulerFactory: SchedulerFactory
    val dataCache: DataCache

    @get:Type("activity_stack")
    val activityStack: MutableList<String>

    @get:Type("current_activity_stack")
    val currentActivityStack: MutableList<String>
}
