package com.example.newsapp.sourcesdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.Constants
import com.example.newsapp.api.model.SourcesItem

@Database(entities = [SourcesItem::class], version = 1,exportSchema = false)
abstract class SourcesDatabase :RoomDatabase(){

    abstract fun  getSourcesDao():SourcesDao

    companion object{
        var sourcesDatabase:SourcesDatabase?=null

        fun init(context: Context){
            if (sourcesDatabase == null){
                sourcesDatabase = Room.databaseBuilder(context, SourcesDatabase::class.java,Constants.SOURCES_DB)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

        fun getInstance():SourcesDatabase{
            return sourcesDatabase!!
        }
    }

}