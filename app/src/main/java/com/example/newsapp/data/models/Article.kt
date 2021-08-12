package com.example.newsapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

const val ArticlesTable = "articles_table"


@Entity(tableName = ArticlesTable)
@Parcelize
data class Article(
    @PrimaryKey
    val title: String,
    val author: String,
    val description: String,
    val publishedAt: Date,
    val source: String,
    val url: String,
    val urlToImage: String,
    var isBookmarked: Boolean = false
) : Parcelable