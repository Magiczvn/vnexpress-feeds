package com.tamnn.vnexpressfeeds.data.rssfeed

import com.tamnn.vnexpressfeeds.data.ApiException
import com.tamnn.vnexpressfeeds.repository.datasource.RssFeedDataSource
import com.tamnn.vnexpressfeeds.repository.model.Article
import com.tamnn.vnexpressfeeds.repository.model.Channel
import com.tamnn.vnexpressfeeds.util.RegexUtil

class ApiRssFeedDataSource(private val _Api: RssFeedApi):RssFeedDataSource{
    override fun getRssFeed(): Channel {
        val response = _Api.getRssFeed().execute()
        return if (response.isSuccessful) {
            val body = response.body()?: throw ApiException("Body is empty")
            val channel = body.channel?: throw ApiException("Channel is empty")
            val articles = channel.item?.map {
                val imageUrl = RegexUtil.getImageUrl(it.description.orEmpty()).orEmpty()
                Article(it.title.orEmpty(), imageUrl, it.pubDate.orEmpty(), it.link.orEmpty())
            }
            Channel(channel.title.orEmpty(), channel.description.orEmpty(), channel.image?.url.orEmpty(), articles.orEmpty())
        } else {
            throw ApiException("Error ${response.code()}")
        }
    }
}