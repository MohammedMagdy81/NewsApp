package com.example.newsapp.repository.impl

import com.example.newsapp.Constant
import com.example.newsapp.database.NewsDatabase
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.network.NewsWebService
import com.example.newsapp.repository.dataSource.NewsOfflineDataSource
import com.example.newsapp.repository.dataSource.NewsOnlineDataSource

class NewsOnlineDataSourceImpl(val webService: NewsWebService, ) : NewsOnlineDataSource {

    override suspend fun getNews(sourceId: String): List<ArticlesItem?> {
        val newsResponse = webService.getNews(Constant.API_KEY, sourceId)
        return newsResponse.articles!!
    }

}

class NewsOfflineDataSourceImpl(val database: NewsDatabase) : NewsOfflineDataSource {

    override suspend fun getNews(): List<ArticlesItem> {
        val news=database.newsDao().getNews()
        return news
    }

    override suspend fun cacheNews(news: List<ArticlesItem?>?) {
        database.newsDao().cacheNews(news)
    }

}