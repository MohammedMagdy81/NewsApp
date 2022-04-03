package com.example.newsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.Constants
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.R
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.api.model.SourcesItem
import com.example.newsapp.databinding.ActivityHomeBinding
import com.example.newsapp.localdata.NewsDataBase
import com.example.newsapp.localdata.NewsModel
import com.example.newsapp.ui.detailes.DetailesActivity
import com.example.newsapp.ui.listnews.NewsListActivity
import com.example.todoapp.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewModel,ActivityHomeBinding>() ,TabLayout.OnTabSelectedListener,
    NewsAdapter.OnItemClick{
    //9310ffe42c424e46b5e039f2877480d4

    lateinit var adapter: NewsAdapter
    private val homeViewModel:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViews()
        viewModel.getSources()
        observeToLiveData()
        //getSources()
    }

    private fun observeToLiveData() {
        viewModel.showProgressLiveData.observe(this, Observer { show->
            if (show){
                viewBinding.progress.visibility=View.VISIBLE
            }else{
                viewBinding.progress.visibility=View.GONE
            }
        })
        viewModel.messageLiveData.observe(this, Observer { message->
            showDialog(message =message,posActionName ="Ok",posAction = { dialogInterface, i ->
                           dialogInterface.dismiss()
            })
        })
        viewModel.sourcesLiveData.observe(this, Observer {sourcesItem->
            showSourcesInTabs(sourcesItem)
        })
        viewModel.newsLiveData.observe(this, Observer { articlesItem->
                showNewsInRecyclerView(articlesItem)
        })
    }
    private fun setUpViews() {
        adapter= NewsAdapter(null,this)
        viewBinding.newsRecyclerView.adapter=adapter
        viewBinding.fabSave.setOnClickListener {
            goToDetailesListActivity()
        }


    }

    private fun goToDetailesListActivity() {
        val intent= Intent(this,NewsListActivity::class.java)
        startActivity(intent)
    }

    private fun showSourcesInTabs(sources: List<SourcesItem?>?) {
            sources?.forEach { item->
                val tab = viewBinding.tabLayout.newTab()
                tab.text= item?.name
                tab.setTag(item)
                viewBinding.tabLayout.addTab(tab)
            }
        viewBinding.tabLayout.addOnTabSelectedListener(this)
        viewBinding.tabLayout.getTabAt(0)?.select()
    }
    private fun showNewsInRecyclerView(articles: List<ArticlesItem?>?) {
            adapter.changeData(articles)
    }


    override fun onTabSelected(tab: TabLayout.Tab?) {
        val item = tab?.tag as SourcesItem
         viewModel.getNews(item.id)
    }
    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }
    override fun onTabReselected(tab: TabLayout.Tab?) {
        val item = tab?.tag as SourcesItem
        viewModel.getNews(item.id)
    }

    override fun initializeViewMode(): HomeViewModel {
        return homeViewModel
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_home
    }

    override fun onNewsItemClick(newsItem: ArticlesItem) {
        goToDetailsOrSaveActivity(newsItem)
    }

    private fun goToDetailsOrSaveActivity(newsItem:ArticlesItem) {
        val title = newsItem.title
        val desc = newsItem.description
        val date = newsItem.publishedAt
        val url = newsItem.urlToImage
        val content= newsItem.content
        val uri = newsItem.url
        showDialog(message = "Show Details Or Save ?" ,posActionName = "Details",negActionName = "Save",
        posAction = { dialog, i ->
            val intent= Intent(this,DetailesActivity::class.java)
            intent.putExtra(Constants.TITLY_KEY,title)
            intent.putExtra(Constants.DESC_KEY,content)
            intent.putExtra(Constants.DATE_KEY,date)
            intent.putExtra(Constants.IMAGE_KEY,url)
           intent.putExtra(Constants.URI_KEY,uri)
            startActivity(intent)
        },negAction = { dialog, i ->
                val newsModel= NewsModel(title = title.toString(),desc =desc,date = date,urlToImage = url , url = uri )
                NewsDataBase.getInstance(this).getNewsDao().addNews(newsModel)
                dialog.dismiss()
                makeToast("This Item Saved Successfully")
            }
      )

    }


}



