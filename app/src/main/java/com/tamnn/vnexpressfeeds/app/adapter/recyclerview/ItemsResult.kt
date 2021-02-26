package com.tamnn.vnexpressfeeds.app.adapter.recyclerview

class ItemsResult {

    private var _Items: List<Item>? = null

    fun popItems(): List<Item>? {
        synchronized(this) {
            val items = _Items
            _Items = null
            return items
        }
    }

    fun pushItems(items: List<Item>) {
        synchronized(this) {
            _Items = items
        }
    }
}