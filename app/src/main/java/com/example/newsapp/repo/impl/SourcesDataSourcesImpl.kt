package com.example.newsapp.repo.impl

import com.example.newsapp.Constants
import com.example.newsapp.api.WebService
import com.example.newsapp.api.model.SourcesItem
import com.example.newsapp.repo.datasources.SourcesOfflineDataSources
import com.example.newsapp.repo.datasources.SourcesOnlineDataSources
import com.example.newsapp.sourcesdatabase.SourcesDatabase

class SourcesOnlineDataSourcesImp(val webService: WebService) : SourcesOnlineDataSources{

    // this function to retrieve data from API
    override suspend fun getSources(): List<SourcesItem?>? {
        val sourcesResponse =  webService.getNewsSources(Constants.API_KEY,"en")
        return sourcesResponse.sources
    }

}

class SourcesOfflineDataSourcesImpl(val database:SourcesDatabase) : SourcesOfflineDataSources{
    // this function to retrieve data from API
    override suspend fun getSources(): List<SourcesItem> {
        val sources = database.getSourcesDao().getSourcesItem()
        return sources
    }

    override suspend fun cacheSources(data: List<SourcesItem?>?) {
        database.getSourcesDao().cacheSourcesItem(data)
    }

}