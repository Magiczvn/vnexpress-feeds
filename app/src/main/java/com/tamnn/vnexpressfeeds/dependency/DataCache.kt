package com.tamnn.vnexpressfeeds.dependency

import com.tamnn.vnexpressfeeds.mvp.Screen

interface DataCache {

    fun putScreen(key: String, screen: Screen)

    fun popScreen(key: String): Screen?
}