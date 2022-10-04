package com.example.newsapp.repository.dataSource

import com.example.newsapp.model.ArticlesItem

interface NewsOnlineDataSource{
   suspend fun getNews(sourceId:String):List<ArticlesItem?>?
}

interface NewsOfflineDataSource{
    suspend fun getNews():List<ArticlesItem?>?
    suspend fun cacheNews(news:List<ArticlesItem?>?)
}