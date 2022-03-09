package com.example.newsapp.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Constants
import com.example.newsapp.NewsAdapter
import com.example.newsapp.R
import com.example.newsapp.api.ApiManeger
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.NewsResponse
import com.example.newsapp.api.model.SourcesItem
import com.example.newsapp.api.model.SourcesResponse
import com.example.todoapp.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : BaseActivity() ,TabLayout.OnTabSelectedListener{
    //9310ffe42c424e46b5e039f2877480d4
    lateinit var progressBar: ProgressBar
    lateinit var tabLayout: TabLayout
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NewsAdapter
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpViews()
        homeViewModel.getSources()
        observeToLiveData()
        //getSources()
    }

    private fun observeToLiveData() {
        homeViewModel.showProgressLiveData.observe(this, Observer { show->
            if (show){
                progressBar.visibility=View.VISIBLE
            }else{
                progressBar.visibility=View.GONE
            }
        })
        homeViewModel.messageLiveData.observe(this, Observer { message->
            showDialog(message =message,posActionName ="Ok",posAction = { dialogInterface, i ->
                           dialogInterface.dismiss()
            })
        })
        homeViewModel.sourcesLiveData.observe(this, Observer {sourcesItem->
            showSourcesInTabs(sourcesItem)
        })
        homeViewModel.newsLiveData.observe(this, Observer { articlesItem->
                showNewsInRecyclerView(articlesItem)
        })
    }
    private fun setUpViews() {
        progressBar=findViewById(R.id.progress)
        tabLayout=findViewById(R.id.tabLayout)
        recyclerView=findViewById(R.id.news_recyclerView)
        homeViewModel= ViewModelProvider(this).get(HomeViewModel::class.java)
        adapter= NewsAdapter(null)
        recyclerView.adapter=adapter


    }
    private fun showSourcesInTabs(sources: List<SourcesItem?>?) {
            sources?.forEach { item->
                val tab = tabLayout.newTab()
                tab.text= item?.name
                tab.setTag(item)
                tabLayout.addTab(tab)
            }
        tabLayout.addOnTabSelectedListener(this)
        tabLayout.getTabAt(0)?.select()
    }
    private fun showNewsInRecyclerView(articles: List<ArticlesItem?>?) {
            adapter.changeData(articles)
    }


    override fun onTabSelected(tab: TabLayout.Tab?) {
        val item = tab?.tag as SourcesItem
         homeViewModel.getNews(item.id)
    }
    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }
    override fun onTabReselected(tab: TabLayout.Tab?) {
        val item = tab?.tag as SourcesItem
        homeViewModel.getNews(item.id)
    }



}



