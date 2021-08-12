package com.example.newsapp.api

import com.example.newsapp.data.models.NewsRemoteDto
import com.example.newsapp.utils.Const
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("q")
        query: String = "apple",
        @Query("country")
        country: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("pageSize")
        pageSize: Int = Const.PAGE_SIZE,
        @Query("apiKey")
        apiKey: String = Const.API_KEY
    ):  NewsRemoteDto

    @GET("everything")
    suspend fun getEverything(
        @Query("q")
        query: String = "apple",
        @Query("page")
        pageNumber: Int = 1,
        @Query("pageSize")
        pageSize: Int = Const.PAGE_SIZE,
        @Query("apiKey")
        apiKey: String = Const.API_KEY
    ): NewsRemoteDto

}