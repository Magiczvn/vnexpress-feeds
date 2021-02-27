package com.tamnn.vnexpressfeeds.mvp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

abstract class MvpFragment<V, P : Presenter<V, S>, S : ViewState> : Fragment() {

    protected var mContext: Context? = null
    protected var mMvpDelegate = MvpDelegate<V, P, S>()

    protected var mPresenterFactory: PresenterFactory<P> = object : PresenterFactory<P> {
        override fun createPresenter(): P {
            return onCreatePresenter(mContext)
        }
    }

    protected var mViewStateFactory: ViewStateFactory<S> = object : ViewStateFactory<S> {

        override fun createViewState(): S {
            return onCreateViewState(mContext)
        }

        override fun getViewStateTag(): String {
            return this@MvpFragment.viewStateTag
        }

    }

    /**
     * The default implementation try cast the Activity to CacheFactory.
     *
     * @return The CacheFactory object.
     */
    protected val cacheFactory: CacheFactory
        get() {
            val activity = activity
            return if (activity is CacheFactory)
                activity
            else
                throw RuntimeException(javaClass.toString() + " must be attached to an Activity that implements " + CacheFactory::class.java)
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cacheFactory = cacheFactory
        mMvpDelegate.onCreate(cacheFactory.presenterCache, cacheFactory.viewStateCache, savedInstanceState, mPresenterFactory, mViewStateFactory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMvpDelegate.onViewCreated(this as V)
    }

    override fun onResume() {
        super.onResume()
        mMvpDelegate.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMvpDelegate.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMvpDelegate.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mMvpDelegate.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMvpDelegate.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }

    /**
     * @return The Presenter that will attach this view.
     */
    abstract fun onCreatePresenter(context: Context?): P

    /**
     * @return The ViewState will be bound with the Presenter.
     */
    abstract fun onCreateViewState(context: Context?): S

    /**
     * @return An unique tag for the ViewState.
     */
    abstract val viewStateTag: String

    val presenter: P get() = mMvpDelegate.presenter

    protected fun isDestroyedBySystem(): Boolean {
        return mMvpDelegate.isDestroyedBySystem
    }
}