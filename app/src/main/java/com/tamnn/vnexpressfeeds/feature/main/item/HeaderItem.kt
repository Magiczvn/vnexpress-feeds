package com.tamnn.vnexpressfeeds.feature.main.item

import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.Item

class HeaderItem(val title: String, val desc: String, val imageUrl: String): Item {

    override fun hashCode() = super.hashCode()

    override fun equals(other: Any?) = other is HeaderItem
}