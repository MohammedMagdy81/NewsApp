package com.example.newsapp.repository.impl

import com.example.newsapp.internet.NetworkAwereHandler
import com.example.newsapp.model.SourcesItem
import com.example.newsapp.repository.SourcesRepository
import com.example.newsapp.repository.dataSource.SourcesOfflineDataSource
import com.example.newsapp.repository.dataSource.SourcesOnlineDataSource

class SourcesRepositoryImpl(val sourcesOnlineDataSource: SourcesOnlineDataSource,
                            val sourcesOfflineDataSource : SourcesOfflineDataSource,
                            val networkAwereHandler: NetworkAwereHandler
):SourcesRepository {

    override suspend fun getSources(): List<SourcesItem?>? {
        if (networkAwereHandler.isOnline()){
            val sources = sourcesOnlineDataSource.getSources()!!
            cacheSources(sources)
            return sources
        }
        return sourcesOfflineDataSource.getSources()
    }

    override suspend fun cacheSources(sources: List<SourcesItem?>?) {
        sourcesOfflineDataSource.cacheSources(sources)
    }
}