package com.tamnn.vnexpressfeeds.mvp

interface Presenter<in V, in S : ViewState> {
    /**
     * Called when the presenter gets created.
     * Note that because presenters survive configuration changes,
     * onCreate will not get called every time your associated view(fragment) gets created.
     * @param viewState A ViewState associated with this Presenter.
     */
    fun onCreate(viewState: S)

    /**
     * Attach your view to this presenter.
     * @param view The view will associated with this presenter.
     */
    fun onAttachView(view: V)

    /**
     * Called when the view go visible (Fragment onResume()).
     */
    fun onViewVisible()

    /**
     * Called when the view go hiding (Fragment onPause()).
     */
    fun onViewHide()

    /**
     * Detach your view from this presenter.
     */
    fun onDetachView()

    /**
     * Called when the presenter is destroyed.
     * Note that because presenters survive configuration changes,
     * onDestroy will not get called every time your associated view(fragment) is destroyed.
     * This method is intended for cleanup, for example, cancelling a useless request.
     */
    fun onDestroy()
}