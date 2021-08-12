package com.example.newsapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.api.NewsAPI
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.toDomainModel
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val newsApi: NewsAPI,
    private val requestType: Enum<Request>
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val response = when(requestType) {
                Request.TOP -> newsApi.getTopHeadlines(pageNumber = page)
                else -> newsApi.getEverything(pageNumber = page)
            }

            val data = response.articles.map { it.toDomainModel() }

            return LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.articles.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

enum class Request {TOP, EVERYTHING}