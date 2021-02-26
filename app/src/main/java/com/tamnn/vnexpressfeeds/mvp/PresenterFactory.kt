package com.tamnn.vnexpressfeeds.mvp

interface PresenterFactory<out P : Presenter<*, *>> {

    fun createPresenter(): P
}