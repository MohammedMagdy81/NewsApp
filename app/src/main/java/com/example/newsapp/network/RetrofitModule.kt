package com.example.newsapp.network

import android.util.Log
import com.example.newsapp.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideWebService(retrofit: Retrofit):NewsWebService{
        return retrofit.create(NewsWebService::class.java)
    }

    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,
                        client:OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideLoginInterceptor() : HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor { message ->
            Log.d("News Api", message)
        }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return interceptor
    }


}