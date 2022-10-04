package com.example.newsapp.repository

import com.example.newsapp.model.SourcesItem

interface SourcesRepository {
    suspend fun getSources():List<SourcesItem?>?
    suspend fun cacheSources(sources :List<SourcesItem?>?)
}