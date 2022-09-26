package com.richiejoel.febonews.data.network

import com.richiejoel.febonews.data.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface NewsAPIClient {

    @GET("top-headlines?country=us&apiKey=5ce08b8488c340488a52b921114878df&page=1&pageSize=10")
    suspend fun getAllNews(): Response<NewsModel>?

}