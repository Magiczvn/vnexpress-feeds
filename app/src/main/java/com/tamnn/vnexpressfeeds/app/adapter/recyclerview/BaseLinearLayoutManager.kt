package com.tamnn.vnexpressfeeds.app.adapter.recyclerview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

open class BaseLinearLayoutManager(context: Context)
    : LinearLayoutManager(context) {

    var canScroll = true

    override fun canScrollVertically(): Boolean {
        return canScroll && super.canScrollVertically()
    }
}