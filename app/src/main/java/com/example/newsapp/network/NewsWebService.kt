package com.example.newsapp.network

import com.example.newsapp.Constant
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsWebService {

    @GET(Constant.END_POINT_SOURCES)
    suspend fun getNewsSources(
        @Query("apiKey") apiKey: String
    ): SourcesResponse

    @GET(Constant.END_POINT_NEWS)
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String
    ): NewsResponse

}