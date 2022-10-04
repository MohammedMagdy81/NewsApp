package com.example.newsapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.ArticlesItem

@Dao
interface NewsDao {

    @Query("select * from articlesitem")
    suspend fun getNews():List<ArticlesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheNews(news:List<ArticlesItem?>?)
}