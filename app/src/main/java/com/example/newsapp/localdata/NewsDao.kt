package com.example.newsapp.localdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Insert
    fun addNews(newsModel: NewsModel)

    @Delete
    fun deleteNews(newsModel: NewsModel)

    @Query("Select * from NewsModel")
    fun getAllNews():List<NewsModel?>?

}