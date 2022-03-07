package com.example.newsapp.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class ApiManeger  {



    companion object{
        private var retrofit:Retrofit?=null

        fun getRetrofitInstance():Retrofit{
            if (retrofit==null){
                val interceptor= HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger{
                    override fun log(message: String) {
                       Log.v("Api",message)
                    }
                })
                interceptor.level=HttpLoggingInterceptor.Level.BODY
                val client= OkHttpClient.Builder().addInterceptor(interceptor).build()
                retrofit=Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        fun getApi() :WebService{
            return getRetrofitInstance().create(WebService::class.java)
        }
    }
}