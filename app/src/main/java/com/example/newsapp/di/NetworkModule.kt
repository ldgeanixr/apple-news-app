package com.example.newsapp.di

import com.example.newsapp.api.NewsAPI
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.utils.Const
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideLoggingInterceptor() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { get<Retrofit>().create(NewsAPI::class.java) }

    single { NewsRepository(get(), get()) }
}

fun provideLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun provideHttpClient(httpLoggingInterceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(Const.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Const.READ_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}