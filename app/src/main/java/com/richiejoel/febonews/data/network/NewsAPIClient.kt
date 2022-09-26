package com.richiejoel.febonews.data.network

import com.richiejoel.febonews.data.model.Constants
import com.richiejoel.febonews.data.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIClient {

    //@GET("top-headlines?country=us&apiKey=5ce08b8488c340488a52b921114878df&page=1&pageSize=10")

    @GET("top-headlines?")
    suspend fun getAllNews(@Query("country") country: String = "us", @Query("apiKey") apiKey: String = Constants.API_KEY, @Query("page") page: String,  @Query("pageSize") pageSize: String = "4" ): Response<NewsModel>?

}