package com.tamnn.vnexpressfeeds.domain


import com.tamnn.vnexpressfeeds.repository.model.Channel
import io.reactivex.Single

interface UseCaseFactory {
    fun getRssFeed(): Single<Channel>
}