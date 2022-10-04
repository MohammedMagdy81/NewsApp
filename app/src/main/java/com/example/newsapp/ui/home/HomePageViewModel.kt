package com.example.newsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Constant
import com.example.newsapp.database.NewsDatabase
import com.example.newsapp.database.SourcesDatabase
import com.example.newsapp.internet.NetworkAwereHandler
import com.example.newsapp.internet.NetworkAwereHandlerImp
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.SourcesItem
import com.example.newsapp.network.NewsWebService
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.repository.SourcesRepository
import com.example.newsapp.repository.dataSource.NewsOfflineDataSource
import com.example.newsapp.repository.dataSource.NewsOnlineDataSource
import com.example.newsapp.repository.dataSource.SourcesOfflineDataSource
import com.example.newsapp.repository.dataSource.SourcesOnlineDataSource
import com.example.newsapp.repository.impl.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class HomePageViewModel @Inject constructor(
    val sourcesRepository: SourcesRepository,
    val newsRepository: NewsRepository
) : ViewModel() {

    val showProgressLiveData = MutableLiveData<Boolean>()
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val messageLiveData = MutableLiveData<String>()
    val newsLiveData = MutableLiveData<List<ArticlesItem?>?>()


    fun getSources() {
        showProgressLiveData.value = true
        try {
            viewModelScope.launch {
                val sources = sourcesRepository.getSources()
                sourcesLiveData.value = sources
                showProgressLiveData.value = false
            }
        } catch (e: Exception) {
            messageLiveData.value = e.localizedMessage
        }

    }

    fun getNews(sourceId: String?) {
        //adapter.setData(null)
        showProgressLiveData.value = true
        try {
            viewModelScope.launch {
                val news = newsRepository.getNews(sourceId!!)
                newsLiveData.value = news
                showProgressLiveData.value = false

            }
        } catch (e: Exception) {
            showProgressLiveData.value = false
            messageLiveData.value = e.localizedMessage
        }


    }
}