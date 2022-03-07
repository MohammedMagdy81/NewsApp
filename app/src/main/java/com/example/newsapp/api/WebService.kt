package com.example.newsapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("sources")
    fun getSources(@Query("apiKey") apiKey : String,
                   @Query("language") language : String,
                    @Query("country") country : String) : Call<SourcesResponse>
}