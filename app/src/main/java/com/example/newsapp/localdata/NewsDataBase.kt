package com.example.newsapp.localdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsModel::class],version = 2,exportSchema = false)
abstract class NewsDataBase : RoomDatabase() {

    abstract fun getNewsDao():NewsDao

    companion object{
        private var database:NewsDataBase?=null
        private val DATA_BASE_NAME  ="news-db"

        fun getInstance(context: Context):NewsDataBase{
            if (database== null){
                database= Room.databaseBuilder(context,NewsDataBase::class.java, DATA_BASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return database!!
        }
    }
}