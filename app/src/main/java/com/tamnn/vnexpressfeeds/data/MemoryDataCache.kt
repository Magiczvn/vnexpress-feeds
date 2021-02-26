package com.tamnn.vnexpressfeeds.data

import com.tamnn.vnexpressfeeds.dependency.DataCache
import com.tamnn.vnexpressfeeds.mvp.Screen
import java.util.concurrent.ConcurrentHashMap

class MemoryDataCache : DataCache {
    private val mScreenMaps = ConcurrentHashMap<String, Screen>()

    override fun putScreen(key: String, screen: Screen) {
        mScreenMaps[key] = screen
    }

    override fun popScreen(key: String): Screen? {
        return mScreenMaps.remove(key)
    }
}