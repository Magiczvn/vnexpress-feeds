package com.tamnn.vnexpressfeeds.mvp

import java.lang.ref.WeakReference

open class BaseViewState : ViewState {

    private val _Presenters: MutableList<WeakReference<Presenter<*, *>>> = ArrayList()

    override var tag: String = ""

    override fun onRestore(data: String?) {
    }

    override fun onSave(): String? = null

    override fun onBind(presenter: Presenter<*, *>) {
        var exist = false
        for (i in _Presenters.indices.reversed()) {
            val ref = _Presenters[i]
            if (ref.get() == null) {
                _Presenters.removeAt(i)
            } else if (ref.get() === presenter) {
                exist = true
            }
        }

        if (!exist) {
            _Presenters.add(WeakReference<Presenter<*, *>>(presenter))
        }
    }

    override fun onUnbind(presenter: Presenter<*, *>): Boolean {
        for (i in _Presenters.indices.reversed()) {
            val ref = _Presenters[i]
            if (ref.get() == null || ref.get() === presenter)
                _Presenters.removeAt(i)
        }

        return _Presenters.isEmpty()
    }
}