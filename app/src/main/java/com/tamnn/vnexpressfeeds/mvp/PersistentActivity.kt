package com.tamnn.vnexpressfeeds.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class PersistentActivity: AppCompatActivity(), CacheFactory {

    private lateinit var mDelegate: PersistentActivityDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        mDelegate = PersistentActivityDelegate()
        mDelegate.onCreate(savedInstanceState, lastCustomNonConfigurationInstance)

        super.onCreate(null) //prevent auto re-create fragment
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mDelegate.onSaveInstanceState(outState)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return mDelegate.getPersistentObject()
    }

    override val presenterCache: PresenterCache
        get() = mDelegate

    override val viewStateCache: ViewStateCache
        get() = mDelegate
}