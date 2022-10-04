package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.database.dao.SourcesDao
import com.example.newsapp.model.SourcesItem

@Database(entities = [SourcesItem::class], version = 2, exportSchema = false)
abstract class SourcesDatabase: RoomDatabase(){

    abstract fun sourcesDao(): SourcesDao

    companion object{
        var dataBase:SourcesDatabase?=null

        fun init(context: Context){
            dataBase= Room.databaseBuilder(context,SourcesDatabase::class.java,"NEWS-DB")
                .fallbackToDestructiveMigration()
                .build()

        }

        fun getInstance():SourcesDatabase{
            return dataBase!!
        }
    }



}