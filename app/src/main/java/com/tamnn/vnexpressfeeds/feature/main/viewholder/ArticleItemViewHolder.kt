package com.tamnn.vnexpressfeeds.feature.main.viewholder

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.bumptech.glide.RequestManager
import com.tamnn.vnexpressfeeds.R
import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.BaseItemViewHolder
import com.tamnn.vnexpressfeeds.feature.main.item.ArticleItem
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.article_item_viewholder.view.*
import kotterknife.bindView

class ArticleItemViewHolder(parent: ViewGroup,
                            resId: Int,
                            private val _EventSubject: Subject<Any>,
                            private val _Glide: RequestManager) :BaseItemViewHolder<ArticleItem>(parent, resId) {

    private val _TitleView: TextView by bindView(R.id.txtTitle)
    private val _ImageView: ImageView by bindView(R.id.imgArticle)

    init {
        this.itemView.setOnClickListener {
            onClick()
        }
    }

    private fun onClick (){

    }

    override fun onBindItem(item: ArticleItem) {
        val oldItem = this.item

        if (oldItem == null || oldItem.title != item.title) {
            _TitleView.text = item.title
        }

        if (oldItem == null || oldItem.imageUrl != item.imageUrl) {
            _Glide.load(item.imageUrl)
                .into(_ImageView)
        }

        super.onBindItem(item)
    }
}