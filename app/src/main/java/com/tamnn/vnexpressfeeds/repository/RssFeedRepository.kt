package com.tamnn.vnexpressfeeds.repository

import com.tamnn.vnexpressfeeds.repository.model.Channel
import io.reactivex.Single

interface RssFeedRepository {
    fun getRssFeed(): Single<Channel>
}