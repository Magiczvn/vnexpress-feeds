package com.tamnn.vnexpressfeeds.feature.main

import android.app.Activity
import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.dependency.ActivityScope

import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory

import dagger.Lazy
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val _Activity: Activity) {

    @Provides
    @ActivityScope
    internal fun provideRssFeedItemBuilder(application: Application,bus: RxBus): ArticleItemBuilder = ArticleItemBuilder(application,bus)

    @Provides
    @ActivityScope
    internal fun provideGlide(): RequestManager = Glide.with(_Activity)

    @Provides
    @ActivityScope
    internal fun provideAdapter(glide: RequestManager): ArticleAdapter = ArticleAdapter(glide)

    @Provides
    internal fun providePresenter(application: Lazy<Application>,
                                  useCaseFactory: Lazy<UseCaseFactory>,
                                  schedulerFactory: Lazy<SchedulerFactory>,
                                   articleItemBuilder: Lazy<ArticleItemBuilder> ): MainContract.Presenter = MainPresenter(application, useCaseFactory, schedulerFactory, articleItemBuilder)
}