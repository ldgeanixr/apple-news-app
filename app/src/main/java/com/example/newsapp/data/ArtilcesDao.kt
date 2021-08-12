package com.example.newsapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.ArticlesTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(article: Article)

    @Query("SELECT * FROM $ArticlesTable")
    fun getAllArticles(): Flow<List<Article>>

}