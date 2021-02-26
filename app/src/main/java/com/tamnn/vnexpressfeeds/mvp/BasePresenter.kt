package com.tamnn.vnexpressfeeds.mvp


open class BasePresenter<V, S : ViewState> : Presenter<V, S> {

    protected var mView: V? = null
    protected lateinit var mViewState: S
    protected var mViewVisible = false

    override fun onCreate(viewState: S) {
        mViewState = viewState
    }

    override fun onAttachView(view: V) {
        mView = view
    }

    override fun onViewVisible() {
        mViewVisible = true
    }

    override fun onViewHide() {
        mViewVisible = false
    }

    override fun onDetachView() {
        mView = null
    }

    override fun onDestroy() {
    }
}