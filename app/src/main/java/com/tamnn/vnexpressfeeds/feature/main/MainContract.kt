package com.tamnn.vnexpressfeeds.feature.main

import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.Item

interface MainContract {

    interface View {
        fun showItems(items: List<Item>)

    }

    interface Presenter:com.tamnn.vnexpressfeeds.mvp.Presenter<View, MainViewState> {

    }
}