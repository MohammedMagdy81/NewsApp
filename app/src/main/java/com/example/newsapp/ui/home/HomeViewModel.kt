package com.example.newsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Constants
import com.example.newsapp.api.WebService
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.NewsResponse
import com.example.newsapp.api.model.SourcesItem
import com.example.newsapp.repo.SourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val sourceRepository:SourcesRepository ,
private val webService: WebService) :ViewModel() {
    // hold data
    // handle logic of app

    val showProgressLiveData=MutableLiveData<Boolean>()
    val messageLiveData=MutableLiveData<String>()
    val sourcesLiveData=MutableLiveData<List<SourcesItem?>?>()
    val newsLiveData=MutableLiveData<List<ArticlesItem?>?>()

     fun getSources() {
         showProgressLiveData.value=true
         viewModelScope.launch {
             try {
                 val sources= sourceRepository.getSources()
                 sourcesLiveData.value= sources
                 showProgressLiveData.value=false

             }catch (e:Exception){
                 showProgressLiveData.value=false
                 messageLiveData.value=e.localizedMessage
             }
         }
    }

     fun getNews(sourcesId: String?) {
         newsLiveData.value=null
        //adapter.changeData(null)
        showProgressLiveData.value=true
        //progressBar.visibility=View.VISIBLE

        webService.getNews(Constants.API_KEY,"en",sourcesId?:"","")
            .enqueue(object :Callback<NewsResponse>{
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    showProgressLiveData.value=false
                    //progressBar.visibility= View.GONE
                    if (response.isSuccessful){
                        showProgressLiveData.value=false
                        //progressBar.visibility= View.GONE
                        newsLiveData.value= response.body()?.articles
                       // showNewsInRecyclerView(response.body()?.articles)

                    }else{
                        messageLiveData.value=response.body()?.message?:""
//                        showDialog(message = response.body()?.message?:"",posActionName ="Ok",posAction = { dialogInterface, i ->
//                            dialogInterface.dismiss()
//                        })
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                   showProgressLiveData.value=false
                    // progressBar.visibility= View.GONE
                    messageLiveData.value= t.localizedMessage
//                    showDialog(message = t.localizedMessage,posActionName ="Retry",posAction = DialogInterface.OnClickListener { dialogInterface, i ->
//                        call.enqueue(this)
//                        dialogInterface.dismiss()
//                    })
                }

            })
    }

}