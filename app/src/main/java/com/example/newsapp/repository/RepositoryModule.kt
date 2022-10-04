package com.example.newsapp.repository

import android.content.Context
import com.example.newsapp.database.SourcesDatabase
import com.example.newsapp.internet.NetworkAwereHandler
import com.example.newsapp.internet.NetworkAwereHandlerImp
import com.example.newsapp.network.NewsWebService
import com.example.newsapp.repository.dataSource.SourcesOfflineDataSource
import com.example.newsapp.repository.dataSource.SourcesOnlineDataSource
import com.example.newsapp.repository.impl.SourcesOfflineDataSourceImpl
import com.example.newsapp.repository.impl.SourcesOnlineDataSourceImpl
import com.example.newsapp.repository.impl.SourcesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideSourcesOnlineDataSource(webService: NewsWebService) : SourcesOnlineDataSource {
        return SourcesOnlineDataSourceImpl(webService)
    }

    @Provides
    fun provideSourcesOfflineDataSource(database: SourcesDatabase): SourcesOfflineDataSource {
        return SourcesOfflineDataSourceImpl(database)
    }

    @Provides
    fun provideNetworkAwereHandler(): NetworkAwereHandler{
        return NetworkAwereHandlerImp.getInstance()
    }

    @Provides
    fun provideSourcesRepository(sourcesOnlineDataSource: SourcesOnlineDataSource,
                                 sourcesOfflineDataSource : SourcesOfflineDataSource,
                                 networkAwereHandler: NetworkAwereHandler): SourcesRepository{

        return SourcesRepositoryImpl(sourcesOnlineDataSource,
                                     sourcesOfflineDataSource,
                                     networkAwereHandler)
    }
}