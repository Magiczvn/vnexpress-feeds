package com.tamnn.vnexpressfeeds.mvp

import android.content.Context
import android.os.Bundle

abstract class MVPActivity<V, out P : Presenter<V, S>, S : ViewState> : PersistentActivity() {

    private var mMvpDelegate = MvpDelegate<V, P, S>()

    private var mPresenterFactory: PresenterFactory<P> = object : PresenterFactory<P> {

        override fun createPresenter(): P {
            return onCreatePresenter(this@MVPActivity)
        }
    }

    private var mViewStateFactory: ViewStateFactory<S> = object : ViewStateFactory<S> {

        override fun createViewState(): S {
            return onCreateViewState(this@MVPActivity)
        }

        override fun getViewStateTag(): String {
            return this@MVPActivity.viewStateTag
        }

    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMvpDelegate.onCreate(presenterCache, viewStateCache, savedInstanceState, mPresenterFactory, mViewStateFactory)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mMvpDelegate.onViewCreated(this as V)
    }

    public override fun onResume() {
        super.onResume()
        mMvpDelegate.onResume()
    }

    public override fun onPause() {
        super.onPause()
        mMvpDelegate.onPause()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMvpDelegate.onSaveInstanceState(outState)
    }

    public override fun onDestroy() {
        super.onDestroy()
        mMvpDelegate.onDestroyView()
        mMvpDelegate.onDestroy()
    }

    /**
     * @return The Presenter that will attach this view.
     */
    abstract fun onCreatePresenter(context: Context): P

    /**
     * @return The ViewState will be bound with the Presenter.
     */
    abstract fun onCreateViewState(context: Context): S

    /**
     * @return An unique tag for the ViewState.
     */
    abstract val viewStateTag: String

    val presenter: P get() = mMvpDelegate.presenter

    protected fun isDestroyedBySystem(): Boolean {
        return mMvpDelegate.isDestroyedBySystem
    }
}