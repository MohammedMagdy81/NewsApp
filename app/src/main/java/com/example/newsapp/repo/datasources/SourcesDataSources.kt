package com.example.newsapp.repo.datasources

import com.example.newsapp.api.model.SourcesItem

interface SourcesOnlineDataSources{
    suspend fun getSources(): List<SourcesItem?>?
}

interface SourcesOfflineDataSources{
    suspend fun getSources(): List<SourcesItem>
    suspend fun cacheSources(data :  List<SourcesItem?>?)
}