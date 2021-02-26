package com.tamnn.vnexpressfeeds.repository.impl

import com.tamnn.vnexpressfeeds.repository.RssFeedRepository
import com.tamnn.vnexpressfeeds.repository.datasource.RssFeedDataSource
import dagger.Lazy

class RssFeedRepositoryImpl (private val _NetworkDataSourceLazy: Lazy<RssFeedDataSource>) : RssFeedRepository {

}