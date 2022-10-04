package com.example.newsapp.repository.impl

import com.example.newsapp.Constant
import com.example.newsapp.database.SourcesDatabase
import com.example.newsapp.model.SourcesItem
import com.example.newsapp.network.NewsWebService
import com.example.newsapp.repository.dataSource.SourcesOfflineDataSource
import com.example.newsapp.repository.dataSource.SourcesOnlineDataSource
import javax.inject.Inject


class SourcesOnlineDataSourceImpl @Inject constructor(val webService: NewsWebService) :SourcesOnlineDataSource{


    override suspend fun getSources(): List<SourcesItem?>? {
        val sourcesResponse = webService.getNewsSources(Constant.API_KEY)
        return sourcesResponse.sources!!

    }

}

class SourcesOfflineDataSourceImpl @Inject constructor(val database:SourcesDatabase) :SourcesOfflineDataSource{
    override suspend fun getSources(): List<SourcesItem?>? {
        val sources = database.sourcesDao().getNewsSources()
        return sources !!
    }

    override suspend fun cacheSources(sources: List<SourcesItem?>?) {
        database.sourcesDao().cacheSources(sources)
    }

}