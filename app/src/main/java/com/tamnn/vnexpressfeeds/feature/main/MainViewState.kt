package com.tamnn.vnexpressfeeds.feature.main


import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.Item
import com.tamnn.vnexpressfeeds.mvp.BaseViewState
import com.tamnn.vnexpressfeeds.repository.model.Channel


class MainViewState(): BaseViewState() {
    var channel:Channel? = null
    var items: List<Item>? = null
}
