package com.example.newsapp.data.models

fun ArticleRemote.toDomainModel(): Article {
    return Article(
        author = author ?: "Unknown",
        description = description,
        publishedAt = publishedAt,
        source = source.name,
        title = title,
        url = url,
        urlToImage = urlToImage,
        isBookmarked = false
    )
}