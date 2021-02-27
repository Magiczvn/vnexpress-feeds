package com.tamnn.vnexpressfeeds.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class BaseMvpFragment<V, P : Presenter<V, S>, S : ViewState, T : Screen> : MvpFragment<V, P, S>() {

    companion object {
        private const val KEY_SCREEN = "BaseMvpFragment_screen"
    }

    val screen: T by lazy {
        arguments?.getParcelable<T>(KEY_SCREEN) as T
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(layoutResource, container, false)

    protected fun setScreen(screen: T) {
        var args: Bundle? = arguments
        if (args == null) {
            args = Bundle()
            arguments = args
        }

        args.putParcelable(KEY_SCREEN, screen)
    }

    protected abstract val layoutResource: Int
}