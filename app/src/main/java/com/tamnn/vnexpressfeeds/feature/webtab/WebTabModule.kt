package com.tamnn.vnexpressfeeds.feature.webtab


import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides

@Module
class WebTabModule {

    @Provides
    internal fun providePresenter(useCaseFactory: Lazy<UseCaseFactory>,
                                  schedulerFactory: Lazy<SchedulerFactory>): WebTabContract.Presenter = WebTabPresenter(useCaseFactory, schedulerFactory)
}
