package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.database.dao.NewsDao
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.SourceItemConverter

@Database(entities = [ArticlesItem::class], version = 1, exportSchema = false)
@TypeConverters(SourceItemConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        var database: NewsDatabase? = null

        fun init(context: Context) {
            database = Room.databaseBuilder(context, NewsDatabase::class.java, "IRTICLES-ITEM-DB")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getInstance(): NewsDatabase {
            return database!!
        }

    }
}