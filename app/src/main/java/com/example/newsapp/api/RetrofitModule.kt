package com.example.newsapp.api

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.temporal.WeekFields

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideWebService(retrofit:Retrofit):WebService{
        return retrofit.create(WebService::class.java)
    }

    @Provides
    fun provideLoginInterceptor():HttpLoggingInterceptor{
        val interceptor= HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.v("Api",message)
            }
        })
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor):OkHttpClient{
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return client
    }


    @Provides
    fun provideGsonConvertorFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient ,gsonConverterFactory: GsonConverterFactory): Retrofit{
        val retrofit= Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
        return retrofit
    }
}