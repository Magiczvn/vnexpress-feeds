package com.tamnn.vnexpressfeeds.repository.impl

import com.tamnn.vnexpressfeeds.repository.RssFeedRepository
import com.tamnn.vnexpressfeeds.repository.datasource.RssFeedDataSource
import com.tamnn.vnexpressfeeds.repository.model.Channel
import dagger.Lazy
import io.reactivex.Single

class RssFeedRepositoryImpl (private val _NetworkDataSourceLazy: Lazy<RssFeedDataSource>) : RssFeedRepository {
    override fun getRssFeed(): Single<Channel> {
        return Single.create<Channel> { emitter ->
            try {
                val rssFeed = _NetworkDataSourceLazy.get()
                    .getRssFeed()

                if (!emitter.isDisposed) {
                    emitter.onSuccess(rssFeed)
                }
            } catch (ex: Exception) {
                if (!emitter.isDisposed) {
                    emitter.onError(ex)
                }
            }
        }
    }
}