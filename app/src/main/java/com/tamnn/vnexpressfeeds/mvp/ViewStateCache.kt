package com.tamnn.vnexpressfeeds.mvp

interface ViewStateCache {
    fun <S : ViewState> getViewState(tag: String?): S?

    fun addViewState(viewState: ViewState)

    fun saveViewStateData(viewState: ViewState)

    fun restoreViewStateData(viewState: ViewState)

    fun removeViewState(viewState: ViewState)
}