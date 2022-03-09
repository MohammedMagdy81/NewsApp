package com.example.newsapp.ui.home

import android.content.DialogInterface
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.Constants
import com.example.newsapp.api.ApiManeger
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.NewsResponse
import com.example.newsapp.api.model.SourcesItem
import com.example.newsapp.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel :ViewModel() {
    // hold data
    // handle logic of app

    val showProgressLiveData=MutableLiveData<Boolean>()
    val messageLiveData=MutableLiveData<String>()
    val sourcesLiveData=MutableLiveData<List<SourcesItem?>?>()
    val newsLiveData=MutableLiveData<List<ArticlesItem?>?>()



     fun getSources() {
        ApiManeger.getApi().getNewsSources(Constants.API_KEY,language = "en")
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    showProgressLiveData.value=false
                    //progressBar.visibility= View.GONE
                    if (response.isSuccessful){
                        showProgressLiveData.value=false
                        //progressBar.visibility= View.GONE
                        sourcesLiveData.value=response.body()?.sources
                        //showSourcesInTabs(response.body()?.sources)
                    }else{
                        messageLiveData.value=response.body()?.message?:""
//                        showDialog(message = response.body()?.message?:"",posActionName ="Ok",posAction = { dialogInterface, i ->
//                            dialogInterface.dismiss()
//                        })
                    }
                }
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    showProgressLiveData.value=false
                    //progressBar.visibility= View.GONE
                    messageLiveData.value=t.localizedMessage
//                    showDialog(message = t.localizedMessage,posActionName ="Retry",posAction = DialogInterface.OnClickListener { dialogInterface, i ->
//                        call.enqueue(this)
//                        dialogInterface.dismiss()
//                    })
                }

            } )

    }
     fun getNews(sourcesId: String?) {
         newsLiveData.value=null
        //adapter.changeData(null)
        showProgressLiveData.value=true
        //progressBar.visibility=View.VISIBLE

        ApiManeger.getApi().getNews(Constants.API_KEY,"en",sourcesId?:"","")
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