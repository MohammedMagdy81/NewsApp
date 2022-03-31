package com.example.newsapp.sourcesdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.api.model.SourcesItem

@Dao
interface SourcesDao {

    @Query("select * from SourcesItem")
    suspend fun getSourcesItem(): List<SourcesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheSourcesItem(data : List<SourcesItem?>?)
}