package com.tamnn.vnexpressfeeds.mvp

interface ViewStateFactory<out S : ViewState> {

    fun createViewState(): S

    fun getViewStateTag(): String
}