package com.tamnn.vnexpressfeeds.feature.main.item

import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.Item

class ArticleItem(val title: String, val imageUrl: String, val pubDate: String, val link: String): Item {

    override fun hashCode() = super.hashCode()

    override fun equals(other: Any?) = other is ArticleItem
}