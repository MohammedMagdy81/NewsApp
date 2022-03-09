package com.example.newsapp.ui.listnews

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.localdata.NewsDataBase
import com.example.newsapp.localdata.NewsModel

class NewsListViewModel : ViewModel() {
    val newsLiveData= MutableLiveData<List<NewsModel?>?>()
    val txtItemNotFound= ObservableField<Boolean>(true)

      fun getNewsFromDb(context: Context) {
        newsLiveData.value = NewsDataBase.getInstance(context).getNewsDao().getAllNews()
          if (newsLiveData.value!!.size!=0){
              txtItemNotFound.set(false)
          }else{
              txtItemNotFound.set(true)
          }

    }




}