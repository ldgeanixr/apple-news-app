package com.example.newsapp.di

import com.example.newsapp.ui.bookmarks.BookmarksViewModel
import com.example.newsapp.ui.everything.EverythingViewModel
import com.example.newsapp.ui.top_headlines.TopHeadlinesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TopHeadlinesViewModel(get()) }
    viewModel { BookmarksViewModel(get()) }
    viewModel { EverythingViewModel(get()) }
}