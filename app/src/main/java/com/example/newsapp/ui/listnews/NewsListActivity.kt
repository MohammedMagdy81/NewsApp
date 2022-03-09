package com.example.newsapp.ui.listnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.Constants
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsListBinding
import com.example.newsapp.databinding.ItemNewsListBinding
import com.example.newsapp.localdata.NewsDataBase
import com.example.newsapp.localdata.NewsListAdapter
import com.example.newsapp.localdata.NewsModel
import com.example.newsapp.ui.detailes.DetailesActivity
import com.example.todoapp.base.BaseActivity

class NewsListActivity : BaseActivity<NewsListViewModel,ActivityNewsListBinding>(), NewsListAdapter.OnItemClick {
    lateinit var adapter :  NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel
        setUpViews()
    }

    private fun setUpViews() {
        adapter=NewsListAdapter(null ,this)
        viewBinding.newsListRecyclerView.adapter =adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.getNewsFromDb(this)
        viewModel.newsLiveData.observe(this, Observer {
            adapter.setData(it)
        })

    }



    override fun initializeViewMode(): NewsListViewModel {
        return ViewModelProvider(this).get(NewsListViewModel::class.java)
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_news_list
    }

    override fun onDeleteItem(newsModel: NewsModel) {
        NewsDataBase.getInstance(this).getNewsDao().deleteNews(newsModel)
        adapter.changeData(NewsDataBase.getInstance(this).getNewsDao().getAllNews())
    }

    override fun onClickedItem(newsModel: NewsModel) {
        val title = newsModel.title
        val desc = newsModel.desc
        val date = newsModel.date
        val url = newsModel.urlToImage

        val intent= Intent(this, DetailesActivity::class.java)
        intent.putExtra(Constants.TITLY_KEY,title)
        intent.putExtra(Constants.DESC_KEY,desc)
        intent.putExtra(Constants.DATE_KEY,date)
        intent.putExtra(Constants.IMAGE_KEY,url)
        startActivity(intent)
    }

}