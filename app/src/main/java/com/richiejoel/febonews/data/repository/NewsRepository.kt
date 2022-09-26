package com.richiejoel.febonews.data.repository

import android.util.Log
import com.richiejoel.febonews.data.model.NewsModel
import com.richiejoel.febonews.data.model.NewsProvider
import com.richiejoel.febonews.data.network.NewsService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val service: NewsService,
    private val newsProvider: NewsProvider
){
    suspend fun getAllNews(page: String): NewsModel?{
        val response = service.getNews(page=page)
        Log.d("Response", response.toString())
        newsProvider.news = response
        return response
    }
}