package com.example.newsapp.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.newsapp.api.NewsAPI
import com.example.newsapp.data.models.Article
import com.example.newsapp.utils.Const

class NewsRepository(
    private val newsApi: NewsAPI,
    private val newsDao: ArticlesDao
) {

    fun getTopHeadlines(): LiveData<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = Const.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsApi, Request.TOP) }
        ).liveData
    }

    fun getEverything(): LiveData<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = Const.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsApi, Request.EVERYTHING) }
        ).liveData
    }

    suspend fun saveArticle(article: Article) {
        newsDao.save(article)
    }

    fun getAllArticles() = newsDao.getAllArticles()

}