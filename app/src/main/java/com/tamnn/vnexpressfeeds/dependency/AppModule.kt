package com.tamnn.vnexpressfeeds.dependency

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.data.MemoryDataCache
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import com.tamnn.vnexpressfeeds.domain.impl.SchedulerFactoryImpl
import com.tamnn.vnexpressfeeds.domain.impl.UseCaseFactoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import java.io.File
import javax.inject.Singleton


@Module
class AppModule(private val _Application: Application) {

    init {

    }

    @Provides
    @Singleton
    internal fun provideApplication(): Application = _Application

    @Provides
    @Singleton
    internal fun provideBus(): RxBus = RxBus()

    @Provides
    @Singleton
    internal fun provideSharesPreferences(application: Application): SharedPreferences = application.getSharedPreferences(application.packageName, Application.MODE_PRIVATE)


    @Provides
    @Singleton
    internal fun provideActivityManager(application: Application): ActivityManager = application.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    @Provides
    @Singleton
    internal fun provideOkHttpCache(application: Application): Cache = Cache(File(application.cacheDir, "HttpResponseCache"), 10L * 1024L * 1024L)

    @Provides
    @Singleton
    internal fun provideSchedulerFactory(): SchedulerFactory = SchedulerFactoryImpl()

    @Provides
    @Singleton
    internal fun provideUseCaseFactory(): UseCaseFactory = UseCaseFactoryImpl()

    @Provides
    @Singleton
    internal fun provideDataCache(): DataCache = MemoryDataCache()

    @Provides
    @Singleton
    @Type("activity_stack")
    internal fun provideActivityStack(): MutableList<String> = ArrayList()

    @Provides
    @Singleton
    @Type("current_activity_stack")
    internal fun provideCurrentActivityStack(): MutableList<String> = ArrayList()

}
