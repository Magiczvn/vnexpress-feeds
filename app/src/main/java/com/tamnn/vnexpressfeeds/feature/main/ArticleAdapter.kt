package com.tamnn.vnexpressfeeds.feature.main

import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.tamnn.vnexpressfeeds.R
import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.BaseItemAdapter
import com.tamnn.vnexpressfeeds.feature.main.item.ArticleItem
import com.tamnn.vnexpressfeeds.feature.main.item.HeaderItem
import com.tamnn.vnexpressfeeds.feature.main.viewholder.ArticleItemViewHolder
import com.tamnn.vnexpressfeeds.feature.main.viewholder.HeaderItemViewHolder

class ArticleAdapter(private val _Glide: RequestManager) : BaseItemAdapter()
{
    companion object {
        private const val ITEM_HEADER = 0
        private const val ITEM_ARTICLE = 1
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItemAt(position)
        return when (item) {
            is ArticleItem -> ITEM_ARTICLE
            is HeaderItem -> ITEM_HEADER
            else -> throw RuntimeException("Not support item $item")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        ITEM_ARTICLE-> ArticleItemViewHolder(parent, R.layout.article_item_viewholder, event, _Glide)
        ITEM_HEADER -> HeaderItemViewHolder(parent, R.layout.header_item_viewholder,event, _Glide)
        else -> throw RuntimeException("Not support type $viewType")
    }

}