package com.tamnn.vnexpressfeeds.domain


import com.tamnn.vnexpressfeeds.repository.model.Channel
import io.reactivex.Single
import java.util.concurrent.Callable

interface UseCaseFactory {
    fun getRssFeed(): Single<Channel>

    fun <T> doWork(callable: Callable<T>): Single<T>
}