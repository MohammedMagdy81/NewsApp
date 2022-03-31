package com.example.newsapp

import android.app.Application
import com.example.newsapp.sourcesdatabase.SourcesDatabase

class MyApp :Application() {
    override fun onCreate() {
        super.onCreate()
        SourcesDatabase.init(this)
        NetworkAwareImp.init(this)
    }
}