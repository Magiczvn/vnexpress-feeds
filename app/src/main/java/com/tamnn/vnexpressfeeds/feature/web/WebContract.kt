package com.tamnn.vnexpressfeeds.feature.web

interface WebContract {

    interface View {


    }

    interface Presenter : com.tamnn.vnexpressfeeds.mvp.Presenter<View, WebViewState> {

    }
}
