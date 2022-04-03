package com.example.newsapp.ui.listnews

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.Constants
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsListBinding
import com.example.newsapp.localdata.NewsDataBase
import com.example.newsapp.adapters.NewsListAdapter
import com.example.newsapp.localdata.NewsModel
import com.example.newsapp.ui.detailes.DetailesActivity
import com.example.todoapp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : BaseActivity<NewsListViewModel,ActivityNewsListBinding>(), NewsListAdapter.OnItemClick {
    lateinit var adapter : NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel
        setUpViews()
        setUpTextChangeListener()
    }

    private fun setUpTextChangeListener() {
        viewBinding.etSearch.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }




    private fun setUpViews() {
        adapter= NewsListAdapter(null ,this)
        viewBinding.newsListRecyclerView.adapter =adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.getNewsFromDb(this)
        viewModel.newsLiveData.observe(this) {
            if (it!!.size>0){
                viewBinding.txtNoItemFound.visibility=View.GONE
            }
            adapter.setData(it)

        }

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
        val intent= Intent(this, DetailesActivity::class.java)
       intent.putExtra(Constants.URI_KEY,newsModel.url)
        startActivity(intent)
    }

}