package com.example.newsapp.di

import androidx.room.Room
import com.example.newsapp.data.ArticlesDao
import com.example.newsapp.data.ArticlesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ArticlesDatabase::class.java,
            "articles-database"
        )
            .build()
    }

    fun getListImagesDao(database: ArticlesDatabase): ArticlesDao {
        return database.articlesDao()
    }

    single {
        getListImagesDao(get())
    }
}