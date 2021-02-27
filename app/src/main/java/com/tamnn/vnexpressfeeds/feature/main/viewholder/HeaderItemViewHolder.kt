package com.tamnn.vnexpressfeeds.feature.main.viewholder

import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.BaseItemViewHolder
import com.tamnn.vnexpressfeeds.feature.main.item.ArticleItem
import io.reactivex.subjects.Subject

class HeaderItemViewHolder(parent: ViewGroup,
                            resId: Int,
                            private val _EventSubject: Subject<Any>,
                            private val _Glide: RequestManager) :BaseItemViewHolder<ArticleItem>(parent, resId) {



    init {
        this.itemView.setOnClickListener {
            onClick()
        }
    }

    private fun onClick (){

    }

    override fun onBindItem(item: ArticleItem) {
        val oldItem = this.item


        super.onBindItem(item)
    }
}