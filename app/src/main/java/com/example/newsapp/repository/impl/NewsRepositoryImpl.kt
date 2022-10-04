package com.example.newsapp.repository.impl

import com.example.newsapp.internet.NetworkAwereHandler
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.repository.dataSource.NewsOfflineDataSource
import com.example.newsapp.repository.dataSource.NewsOnlineDataSource

class NewsRepositoryImpl(val newsOnlineDataSource: NewsOnlineDataSource,
                        val newsOfflineDataSource: NewsOfflineDataSource,
                         val networkAwereHandler: NetworkAwereHandler
) : NewsRepository {
    override suspend fun getNews(sourcesId: String): List<ArticlesItem?>? {
        if (networkAwereHandler.isOnline()) {
            val news = newsOnlineDataSource.getNews(sourcesId)
            cacheNews(news)
            return news
        }
        return newsOfflineDataSource.getNews()
    }

    override suspend fun cacheNews(news: List<ArticlesItem?>?) {
       newsOfflineDataSource.cacheNews(news)
    }


}