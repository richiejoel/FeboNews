package com.richiejoel.febonews.data.network

import android.util.Log
import com.richiejoel.febonews.data.model.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class NewsService @Inject constructor(
    private val api:NewsAPIClient
) {
    suspend fun getNews(): NewsModel? {
        return withContext(Dispatchers.IO) {
            val response: Response<NewsModel>? = api.getAllNews()
            Log.d("Response", response.toString())
            response?.body()
        }
    }
}