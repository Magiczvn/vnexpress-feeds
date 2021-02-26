package com.tamnn.vnexpressfeeds.app.adapter.recyclerview

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

open class BaseItemViewHolder<T : Item>(parent: ViewGroup,
                                        resId: Int)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(resId, parent, false)) {

    var item: T? = null
        private set

    @CallSuper
    open fun onBindItem(item: T) {
        this.item = item
    }

    fun onBindItem1(item: Item) {
        onBindItem(item as T)
    }

    open fun onViewAttachedToWindow() {
    }

    open fun onViewDetachedFromWindow() {
    }
}