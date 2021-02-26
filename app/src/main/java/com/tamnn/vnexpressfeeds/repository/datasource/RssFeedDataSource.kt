package com.tamnn.vnexpressfeeds.repository.datasource

import com.tamnn.vnexpressfeeds.repository.model.Channel

interface RssFeedDataSource {
    fun getRssFeed(): Channel
}