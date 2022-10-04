package com.example.newsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.SourcesItem

@Dao
interface SourcesDao {

    @Query("SELECT * FROM SourcesItem")
    suspend fun getNewsSources():List<SourcesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheSources(data:List<SourcesItem?>?)
}
