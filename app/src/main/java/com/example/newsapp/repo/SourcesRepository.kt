package com.example.newsapp.repo

import com.example.newsapp.api.model.SourcesItem

interface SourcesRepository {

    suspend fun getSources() : List<SourcesItem?>?
    suspend fun cacheSources(data : List<SourcesItem?>?)
}