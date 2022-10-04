package com.example.newsapp.repository

import com.example.newsapp.database.NewsDatabase
import com.example.newsapp.database.SourcesDatabase
import com.example.newsapp.internet.NetworkAwereHandler
import com.example.newsapp.internet.NetworkAwereHandlerImp
import com.example.newsapp.network.NewsWebService
import com.example.newsapp.repository.dataSource.NewsOfflineDataSource
import com.example.newsapp.repository.dataSource.NewsOnlineDataSource
import com.example.newsapp.repository.dataSource.SourcesOfflineDataSource
import com.example.newsapp.repository.dataSource.SourcesOnlineDataSource
import com.example.newsapp.repository.impl.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NewsRepositoryModule {
    @Provides
    fun provideNewsOnlineDataSource(webService: NewsWebService) : NewsOnlineDataSource {
        return NewsOnlineDataSourceImpl(webService)
    }

    @Provides
    fun provideNewsOfflineDataSource(database: NewsDatabase): NewsOfflineDataSource {
        return NewsOfflineDataSourceImpl(database)
    }

    @Provides
    fun provideNewsRepository(newsOnlineDataSource: NewsOnlineDataSource,
                                newsOfflineDataSource : NewsOfflineDataSource,
                                 networkAwereHandler: NetworkAwereHandler
    ): NewsRepository{

        return NewsRepositoryImpl(newsOnlineDataSource,newsOfflineDataSource,networkAwereHandler)
    }
}