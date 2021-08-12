package com.example.newsapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

data class NewsRemoteDto(
    val articles: List<ArticleRemote>,
    val status: String,
    val totalResults: Int
)

@Parcelize
data class ArticleRemote(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: Date,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable

@Parcelize
data class Source(
    val id: String,
    val name: String
) : Parcelable