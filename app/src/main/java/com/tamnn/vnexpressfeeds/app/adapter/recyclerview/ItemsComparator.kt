package com.tamnn.vnexpressfeeds.app.adapter.recyclerview

import androidx.recyclerview.widget.DiffUtil

class ItemsComparator(private val _OldItems: List<Item>?,
                      private val _NewItems: List<Item>)
    : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return _OldItems?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return _NewItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (_OldItems == null)  return false

        val oldItem = _OldItems[oldItemPosition]
        val newItem = _NewItems[newItemPosition]
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }
}