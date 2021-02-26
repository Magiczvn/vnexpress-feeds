package com.tamnn.vnexpressfeeds.app.adapter.recyclerview

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

abstract class BaseItemAdapter : RecyclerView.Adapter<BaseItemViewHolder<out Item>>() {

    val event: Subject<Any> = PublishSubject.create<Any>().toSerialized()
    var items: List<Item>? = null
        private set
    protected var mRecyclerView: RecyclerView? = null
        private set
    protected var mIsDestroyed = false
    private var _Ready = false

    fun onDestroy() {
        mIsDestroyed = true
    }

    fun updateItems(items: List<Item>?) {
        updateItems(items, null)
    }

    fun updateItems(items: List<Item>?, diffResult: DiffUtil.DiffResult?) {
        mRecyclerView?.post {
            if (mRecyclerView?.isComputingLayout == true) {
                updateItems(items, diffResult)
            } else {
                setItemsInternal(items, diffResult)
            }
        }
    }

    fun updateItems(items: List<Item>?, isScrollToLast:Boolean) {
        mRecyclerView?.post {
            if (mRecyclerView?.isComputingLayout == true) {
                updateItems(items, isScrollToLast)
            } else {
                setItemsInternal(items, isScrollToLast)
            }
        }
    }

    fun updateItems(items: List<Item>?, diffResult: DiffUtil.DiffResult?, isScrollToLast:Boolean, showTime: Int, noOfCommentToAdd: Int, noOfCommentToRemove: Int) {
        mRecyclerView?.post {
            if (mRecyclerView?.isComputingLayout == true) {
                updateItems(items, diffResult, isScrollToLast, showTime, noOfCommentToAdd, noOfCommentToRemove)
            } else {
                setItemsInternal(items, diffResult, isScrollToLast, showTime, noOfCommentToAdd, noOfCommentToRemove)
            }
        }
    }

    private fun setItemsInternal(items: List<Item>?, diffResult: DiffUtil.DiffResult?) {
        beginSetItems()
        if (this.items != null && diffResult != null) {
            this.items = items
            diffResult.dispatchUpdatesTo(this)
        } else {
            this.items = items
            notifyDataSetChanged()
        }
        endSetItems()
    }

    //For reach limit livestream video comment
    private fun setItemsInternal(items: List<Item>?, diffResult: DiffUtil.DiffResult?, isScrollToLast: Boolean, showTime: Int, noOfCommentToAdd: Int, noOfCommentToRemove: Int) {
        if (this.items != null && diffResult != null) {
            this.items = items
            notifyItemRangeRemoved(0, noOfCommentToRemove)
            notifyItemRangeChanged(items.orEmpty().size - 1 - noOfCommentToAdd, noOfCommentToAdd )
            if(isScrollToLast && showTime > 0) scrollTolast(showTime)
        } else {
            this.items = items
            notifyDataSetChanged()
        }
    }

    private fun setItemsInternal(items: List<Item>?, isScrollToLast: Boolean) {
        this.items = items
        notifyDataSetChanged()
        scrollTolastForRemainingItem(isScrollToLast)
    }

    open fun scrollTolast(noOfItem: Int) {}

    open fun scrollTolastForRemainingItem(isScrollToLast: Boolean) {}

    open fun beginSetItems() {}

    open fun endSetItems() {}

    fun moveItems(items: List<Item>, fromPosition: Int, toPosition: Int) {
        this.items = items
        if (Math.abs(fromPosition - toPosition) == 1) { // move to next item
            notifyItemMoved(fromPosition, toPosition)
        } else {
            if (fromPosition < toPosition) {
                notifyItemMoved(fromPosition, toPosition)
                notifyItemMoved(toPosition - 1, fromPosition)
            } else {
                notifyItemMoved(fromPosition, toPosition)
                notifyItemMoved(toPosition + 1, fromPosition)
            }
        }
    }

    fun setReady(ready: Boolean) {
        if (_Ready != ready) {
            _Ready = ready
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return if (!_Ready) return 0 else items?.size ?: 0
    }

    fun getItemAt(position: Int): Item? {
        val items = items ?: return null
        if (position < 0 || position > items.size - 1) return null
        return items[position]
    }

    @CallSuper
    override fun onBindViewHolder(holder: BaseItemViewHolder<out Item>, position: Int) {
        getItemAt(position)?.let { holder.onBindItem1(it) }
    }

    override fun onBindViewHolder(holder: BaseItemViewHolder<out Item>, position: Int, payloads: MutableList<Any>) {
        getItemAt(position)?.let { holder.onBindItem1(it) }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    override fun onViewAttachedToWindow(holder: BaseItemViewHolder<out Item>) {
        super.onViewAttachedToWindow(holder)
        holder.onViewAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(holder: BaseItemViewHolder<out Item>) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetachedFromWindow()
    }
}