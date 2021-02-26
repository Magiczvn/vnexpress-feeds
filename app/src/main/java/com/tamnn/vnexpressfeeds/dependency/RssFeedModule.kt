package com.tamnn.vnexpressfeeds.dependency

import com.tamnn.vnexpressfeeds.BuildConfig
import com.tamnn.vnexpressfeeds.data.rssfeed.ApiRssFeedDataSource
import com.tamnn.vnexpressfeeds.data.rssfeed.RssFeedApi
import com.tamnn.vnexpressfeeds.repository.RssFeedRepository
import com.tamnn.vnexpressfeeds.repository.datasource.RssFeedDataSource
import com.tamnn.vnexpressfeeds.repository.impl.RssFeedRepositoryImpl
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
class RssFeedModule {
    @Provides
    @Singleton
    fun provideRssFeedApi(client: OkHttpClient): RssFeedApi = Retrofit.Builder()
        .baseUrl(BuildConfig.ENDPOINT)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
        .create(RssFeedApi::class.java)

    @Provides
    @Singleton
    internal fun provideRssFeedDataSource(api: RssFeedApi): RssFeedDataSource = ApiRssFeedDataSource(api)

    @Provides
    @Singleton
    fun provideRssFeedRepository(networkDataSourceLazy: Lazy<RssFeedDataSource>): RssFeedRepository = RssFeedRepositoryImpl(networkDataSourceLazy)
}