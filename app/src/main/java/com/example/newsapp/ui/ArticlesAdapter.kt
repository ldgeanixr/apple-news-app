package com.example.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.ArticleRemote
import com.example.newsapp.databinding.ItemArticleBinding
import org.ocpsoft.prettytime.PrettyTime

class ArticlesAdapter(
    val onArticleClicked: (Article) -> Unit,
    val onBookmarkClicked: (Article) -> Unit
) : PagingDataAdapter<Article, ArticlesAdapter.ArticleViewHolder>(diffCallback) {


    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article, pos: Int) {
            binding.apply {
                Glide.with(itemView)
                    .load(article.urlToImage)
                    .into(articleImage)

                articleImage.clipToOutline = true
                date.text = PrettyTime().run {
                    format(article.publishedAt)
                }

                bookmarkIcon.setImageResource(handleBookmarkIcon(article.isBookmarked))

                bookmarkIcon.setOnClickListener {
                    article.isBookmarked = !article.isBookmarked
                    bookmarkIcon.setImageResource(handleBookmarkIcon(article.isBookmarked))
                    onBookmarkClicked(article)
                }

                articleTitle.text = article.title
                articleDescription.text = article.description
            }
        }
    }

    private fun handleBookmarkIcon(bookmarked: Boolean): Int {
        return if (bookmarked) {
            R.drawable.ic_bookmark_red
        } else {
            R.drawable.ic_bookmark_black
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { article ->
            holder.bind(article, position)

            holder.itemView.setOnClickListener {
                onArticleClicked(article)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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