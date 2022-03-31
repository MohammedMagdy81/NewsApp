package com.example.newsapp.sourcesdatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    fun provideDatabase():SourcesDatabase{
        return SourcesDatabase.getInstance()
    }
}