package com.example.newsapp.repository.dataSource

import com.example.newsapp.model.SourcesItem

interface SourcesOnlineDataSource  {
    suspend fun getSources():List<SourcesItem?>?
}

interface SourcesOfflineDataSource  {
    suspend fun getSources():List<SourcesItem?>?
    suspend fun cacheSources(sources:List<SourcesItem?>?)

}