package com.example.newsapp.ui.everything

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.data.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EverythingViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun onBookmarkClicked(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.saveArticle(article)
        }
    }

    val articles = newsRepository.getEverything().cachedIn(viewModelScope)

}