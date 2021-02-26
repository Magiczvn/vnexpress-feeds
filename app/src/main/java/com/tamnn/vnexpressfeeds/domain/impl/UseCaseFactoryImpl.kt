package com.tamnn.vnexpressfeeds.domain.impl

import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import com.tamnn.vnexpressfeeds.repository.RssFeedRepository
import com.tamnn.vnexpressfeeds.repository.model.Channel
import dagger.Lazy
import io.reactivex.Single

class UseCaseFactoryImpl(private val _RssFeedRepository: Lazy<RssFeedRepository>): UseCaseFactory{

    override fun getRssFeed(): Single<Channel> {
        return _RssFeedRepository.get().getRssFeed()
    }
}