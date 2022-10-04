package com.example.newsapp

import android.app.Application
import com.example.newsapp.database.NewsDatabase
import com.example.newsapp.database.SourcesDatabase
import com.example.newsapp.internet.NetworkAwereHandlerImp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        SourcesDatabase.init(this)
        NewsDatabase.init(this)
        NetworkAwereHandlerImp.init(this)
    }
}