package com.example.newsapp

import android.app.Application
import com.example.newsapp.sourcesdatabase.SourcesDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp :Application() {
    override fun onCreate() {
        super.onCreate()
        SourcesDatabase.init(this)
        NetworkAwareImp.init(this)
    }
}