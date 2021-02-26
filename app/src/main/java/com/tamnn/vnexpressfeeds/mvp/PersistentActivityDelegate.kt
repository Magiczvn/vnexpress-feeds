package com.tamnn.vnexpressfeeds.mvp

import android.os.Bundle
import androidx.collection.SimpleArrayMap
import androidx.collection.SparseArrayCompat
import android.util.ArrayMap
import java.util.concurrent.atomic.AtomicInteger

class PersistentActivityDelegate : PresenterCache, ViewStateCache {

    companion object {
        private const val KEY_NEXT_PRESENTER_ID = "com.rey.mvp.next_presenter_id"
        private const val KEY_VIEW_STATE_TAG = "com.rey.mvp.view_state_tag"
        private const val KEY_VIEW_STATE_DATA = "com.rey.mvp.view_state_data"
    }

    private lateinit var _PersistentInstance: PersistentInstance

    fun onCreate(savedInstanceState: Bundle?, persistentObj: Any?) {
        var persistentInstance = persistentObj as? PersistentInstance
        if (persistentInstance == null) {
            val seed = savedInstanceState?.getInt(KEY_NEXT_PRESENTER_ID) ?: 0
            persistentInstance = PersistentInstance(seed)

            if (savedInstanceState != null) {
                try {
                    val tags = savedInstanceState.getStringArray(KEY_VIEW_STATE_TAG)
                    val data = savedInstanceState.getStringArray(KEY_VIEW_STATE_DATA)

                    if (tags != null && data != null) {
                        for (i in tags.indices)
                            persistentInstance.viewStatesData[tags[i]] = data[i]
                    }
                } catch (e: Exception) {
                }

            }
        }
        _PersistentInstance = persistentInstance
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_NEXT_PRESENTER_ID, _PersistentInstance.nextPresenterId.get())

        val set = _PersistentInstance.viewStatesData.keys
        val tags = arrayOfNulls<String>(set.size)
        val data = arrayOfNulls<String>(tags.size)

        val iter = set.iterator()
        var index = 0
        while (iter.hasNext()) {
            tags[index] = iter.next()
            data[index] = _PersistentInstance.viewStatesData[tags[index]]
            index++
        }

        outState.putStringArray(KEY_VIEW_STATE_TAG, tags)
        outState.putStringArray(KEY_VIEW_STATE_DATA, data)
    }

    fun getPersistentObject(): Any {
        return _PersistentInstance
    }

    override fun generatePresenterId(): Int {
        return _PersistentInstance.nextPresenterId.getAndIncrement()
    }

    override fun <P : Presenter<*, *>> getPresenter(id: Int): P? {
        return _PersistentInstance.presenters.get(id) as P?
    }

    override fun setPresenter(id: Int, presenter: Presenter<*, *>?) {
        if (presenter == null)
            _PersistentInstance.presenters.remove(id)
        else
            _PersistentInstance.presenters.put(id, presenter)
    }

    override fun <S : ViewState> getViewState(tag: String?): S? {
        return _PersistentInstance.viewStates.get(tag) as S?
    }

    override fun addViewState(viewState: ViewState) {
        _PersistentInstance.viewStates.put(viewState.tag, viewState)
    }

    override fun saveViewStateData(viewState: ViewState) {
        val state = viewState.onSave() ?: return
        _PersistentInstance.viewStatesData[viewState.tag] = state
    }

    override fun restoreViewStateData(viewState: ViewState) {
        val state = _PersistentInstance.viewStatesData[viewState.tag] ?: return
        viewState.onRestore(state)
    }

    override fun removeViewState(viewState: ViewState) {
        _PersistentInstance.viewStates.remove(viewState.tag)
        _PersistentInstance.viewStatesData.remove(viewState.tag)
    }

    private inner class PersistentInstance(seed: Int) {
        val presenters = SparseArrayCompat<Presenter<*, *>>()
        val nextPresenterId = AtomicInteger(seed)
        val viewStates = SimpleArrayMap<String, ViewState>()
        val viewStatesData = ArrayMap<String, String>()
    }
}