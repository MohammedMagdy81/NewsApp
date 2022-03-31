package com.example.newsapp.api

import com.example.newsapp.api.model.NewsResponse
import com.example.newsapp.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("sources")
   suspend fun getNewsSources(@Query("apiKey") apiKey : String,
                   @Query("language") language : String) : SourcesResponse

    @GET("everything")
    fun getNews(@Query("apiKey") apiKey : String,
                @Query("language") language : String ,
                @Query("sources") sources : String,
                @Query("q") searchKey : String) : Call<NewsResponse>

}