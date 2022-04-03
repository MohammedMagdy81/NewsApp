package com.example.newsapp.ui.listnews

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.localdata.NewsDataBase
import com.example.newsapp.localdata.NewsModel

class NewsListViewModel : ViewModel() {
    val newsLiveData= MutableLiveData<List<NewsModel?>?>()

      fun getNewsFromDb(context: Context) {
        newsLiveData.value = NewsDataBase.getInstance(context).getNewsDao().getAllNews()

    }







}