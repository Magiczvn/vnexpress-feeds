package com.tamnn.vnexpressfeeds.domain.impl

import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import com.tamnn.vnexpressfeeds.repository.RssFeedRepository
import com.tamnn.vnexpressfeeds.repository.model.Channel
import dagger.Lazy
import io.reactivex.Single
import java.util.concurrent.Callable

class UseCaseFactoryImpl(private val _RssFeedRepository: Lazy<RssFeedRepository>): UseCaseFactory{

    override fun getRssFeed(): Single<Channel> {
        return _RssFeedRepository.get().getRssFeed()
    }

    override fun <T> doWork(callable: Callable<T>): Single<T> = Single.fromCallable(callable)
}