package com.tamnn.vnexpressfeeds.mvp

interface CacheFactory {

    val presenterCache: PresenterCache

    val viewStateCache: ViewStateCache
}