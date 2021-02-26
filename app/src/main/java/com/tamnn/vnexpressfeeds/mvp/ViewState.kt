package com.tamnn.vnexpressfeeds.mvp

interface ViewState {

    /**
     * An unique tag to save this ViewState's state to ViewStateCache.
     */
    var tag: String

    /**
     * Called when restore the state of this ViewState.
     * @param data
     */
    fun onRestore(data: String?)

    /**
     * Called when save this ViewState's data.
     * @return
     */
    fun onSave(): String?

    /**
     * Called when bind this ViewState to a Presenter. Note that a ViewState can be bound to many Presenters.
     * @param presenter
     */
    fun onBind(presenter: Presenter<*, *>)

    /**
     * Called when unbind this ViewState from a Presenter.
     * @param presenter
     * @return This ViewState isn't bound with any Presenter or not. If true, we should remove this ViewState from cache.
     */
    fun onUnbind(presenter: Presenter<*, *>): Boolean
}