package com.itssuryansh.mvvmapplication.remote

import com.itssuryansh.mvvmapplication.entities.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v2/everything")
    suspend fun getNews(@Query("domains") domains: String, @Query("apiKey") apiKey : String) : Response<News>

}