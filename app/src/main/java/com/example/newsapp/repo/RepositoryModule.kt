package com.example.newsapp.repo

import com.example.newsapp.NetworkAware
import com.example.newsapp.NetworkAwareImp
import com.example.newsapp.api.WebService
import com.example.newsapp.repo.datasources.SourcesOfflineDataSources
import com.example.newsapp.repo.datasources.SourcesOnlineDataSources
import com.example.newsapp.repo.impl.SourcesOfflineDataSourcesImpl
import com.example.newsapp.repo.impl.SourcesOnlineDataSourcesImp
import com.example.newsapp.repo.impl.SourcesRepositoryImpl
import com.example.newsapp.sourcesdatabase.SourcesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideSourcesOnlineDataSources(webService: WebService):SourcesOnlineDataSources{
        return SourcesOnlineDataSourcesImp(webService)
    }

    @Provides
    fun provideSourcesOfflineDataSources(database:SourcesDatabase) : SourcesOfflineDataSources{
        return SourcesOfflineDataSourcesImpl(database)
    }

    @Provides
    fun provideNetworkAware() : NetworkAware{
        return NetworkAwareImp.getInstance()
    }

    @Provides
    fun provideSourcesRepository(sourcesOnlineDataSources: SourcesOnlineDataSources,
                                 sourcesOfflineDataSources: SourcesOfflineDataSources,
                                 networkAware: NetworkAware) : SourcesRepository{

        return SourcesRepositoryImpl(sourcesOnlineDataSources,
        sourcesOfflineDataSources ,
        networkAware)
    }
}



