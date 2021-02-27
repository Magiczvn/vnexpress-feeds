package com.tamnn.vnexpressfeeds.feature.web

import android.app.Activity
import com.tamnn.vnexpressfeeds.dependency.ActivityScope
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides


@Module
class WebModule(private val _Activity: Activity) {

    @Provides
    internal fun providePresenter(useCaseFactory: Lazy<UseCaseFactory>,
                                  schedulerFactory: Lazy<SchedulerFactory>): WebContract.Presenter = WebPresenter(useCaseFactory, schedulerFactory)
}
