package com.example.newsapp.ui.bookmarks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.models.Article
import com.example.newsapp.databinding.ItemBookmarkBinding

class BookmarksAdapter(
    val onArticleClicked: (Article) -> Unit
) : ListAdapter<Article, BookmarksAdapter.ArticleViewHolder>(diffCallback) {

    inner class ArticleViewHolder(private val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article, pos: Int) {
            binding.apply {
                Glide.with(itemView)
                    .load(article.urlToImage)
                    .skipMemoryCache(true)
                    .dontAnimate()
                    .into(articleImage)

                articleImage.clipToOutline = true

                articleTitle.text = article.title
                articleDescription.text = article.description
            }
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position).let { article ->
            holder.bind(article, position)

            holder.itemView.setOnClickListener {
                onArticleClicked(article)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticleViewHolder(binding)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

}