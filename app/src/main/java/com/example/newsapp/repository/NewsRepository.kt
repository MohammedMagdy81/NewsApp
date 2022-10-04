package com.example.newsapp.repository

import com.example.newsapp.model.ArticlesItem

interface NewsRepository {
    suspend fun getNews(sourcesId:String):List<ArticlesItem?>?
    suspend fun cacheNews(news:List<ArticlesItem?>?)
}