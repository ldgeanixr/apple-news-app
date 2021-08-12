package com.example.newsapp.ui.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.newsapp.data.NewsRepository

class BookmarksViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val tasks = newsRepository.getAllArticles().asLiveData()

}


