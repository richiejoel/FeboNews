package com.richiejoel.febonews.data.network

import com.richiejoel.febonews.data.model.Constants
import com.richiejoel.febonews.data.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIClient {

    @GET("everything?")
    suspend fun getAllNews(@Query("q") q: String = "usa", @Query("apiKey") apiKey: String = Constants.API_KEY, @Query("page") page: String,  @Query("pageSize") pageSize: String = "5" ): Response<NewsModel>?

}