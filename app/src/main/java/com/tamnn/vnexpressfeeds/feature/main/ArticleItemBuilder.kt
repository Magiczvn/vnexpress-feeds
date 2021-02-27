package com.tamnn.vnexpressfeeds.feature.main

import android.content.Context
import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.Item
import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.feature.main.item.ArticleItem
import com.tamnn.vnexpressfeeds.repository.model.Channel

class ArticleItemBuilder(private val _Context: Context,
                      private val _Bus: RxBus) {

     fun showRssFeeds(channel: Channel): List<Item> {

         val list = emptyList<Item>().toMutableList()
        channel.articles.forEach {
            list.add(ArticleItem(it.title, it.imageUrl, it.pubDate, it.link))
        }

        return list
    }

}
