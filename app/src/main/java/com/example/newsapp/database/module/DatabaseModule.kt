package com.example.newsapp.database.module

import com.example.newsapp.database.NewsDatabase
import com.example.newsapp.database.SourcesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDataBase(): SourcesDatabase {
        return SourcesDatabase.getInstance()
    }

    @Provides
    fun provideNewsDataBase(): NewsDatabase = NewsDatabase.getInstance()



}