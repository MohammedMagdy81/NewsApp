package com.example.newsapp.repo.impl

import com.example.newsapp.NetworkAware
import com.example.newsapp.api.model.SourcesItem
import com.example.newsapp.repo.SourcesRepository
import com.example.newsapp.repo.datasources.SourcesOfflineDataSources
import com.example.newsapp.repo.datasources.SourcesOnlineDataSources

class SourcesRepositoryImpl(val sourcesOnlineDataSources: SourcesOnlineDataSources,
                            val sourcesOfflineDataSources: SourcesOfflineDataSources,
                            val networkAware: NetworkAware
) : SourcesRepository {

    override suspend fun getSources(): List<SourcesItem?>? {
        // if internet Available
        if (networkAware.isOnline()) {
            val sources = sourcesOnlineDataSources.getSources()
            cacheSources(sources)
            return sources
        }
        return sourcesOfflineDataSources.getSources()
    }

    override suspend fun cacheSources(data: List<SourcesItem?>?) {
        // if No InternetFounded . .
        sourcesOfflineDataSources.cacheSources(data)

    }

}