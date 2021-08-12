package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.dbModule
import com.example.newsapp.di.networkModule
import com.example.newsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApp)
            modules(viewModelModule, networkModule, dbModule)
        }
    }

}