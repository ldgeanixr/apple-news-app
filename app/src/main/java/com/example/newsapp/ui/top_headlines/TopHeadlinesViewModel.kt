package com.example.newsapp.ui.top_headlines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.data.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TopHeadlinesViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val refresh = MutableLiveData(0)
    var count = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                delay(5000)
                count = refresh.value ?: 0
                refresh.postValue(count + 1)
            }
        }
    }

    fun onBookmarkClicked(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.saveArticle(article)
        }
    }

    val articles = newsRepository.getTopHeadlines().cachedIn(viewModelScope)

}