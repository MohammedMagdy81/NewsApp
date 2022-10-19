package com.example.newsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.ActivityHomePageBinding
import com.example.newsapp.model.ArticlesItem
import com.example.newsapp.model.SourcesItem
import com.example.newsapp.ui.details.NewsDetailsActivity
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageActivity : BaseActivity<ActivityHomePageBinding, HomePageViewModel>(),
    TabLayout.OnTabSelectedListener {

    lateinit var adapter: NewsAdapter
    private val homPageViewModel: HomePageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpAdapter()
        viewModel.getSources()
        observeToLiveData()
        setUpClickArticalItem()


    }


    private fun setUpClickArticalItem() {
        adapter.onArticleItemClick = object : NewsAdapter.OnArticleItemClick {
            override fun onItemClick(position: Int, newsItem: ArticlesItem?) {
                val uri = newsItem?.url
                val intent = Intent(this@HomePageActivity, NewsDetailsActivity::class.java)
                intent.putExtra("uri", uri)
                startActivity(intent)

            }

        }
    }

    private fun observeToLiveData() {
        viewModel.messageLiveData.observe(this) { message ->
            showDialog(message = message,
                posActionName = "Ok",
                posAction = { dialog, it ->
                    dialog.dismiss()
                })
        }
        viewModel.showProgressLiveData.observe(this) { show ->
            if (show) {
                viewBinding.progress.visibility = View.VISIBLE
            } else {
                viewBinding.progress.visibility = View.GONE
            }
        }

        viewModel.sourcesLiveData.observe(this) { sourcesItem ->
            showSourcesInTabs(sourcesItem)
        }

        viewModel.newsLiveData.observe(this) { newsList ->
            showNewsInRecyclerView(newsList)
        }
    }


    private fun setUpAdapter() {
        adapter = NewsAdapter(null);
        viewBinding.newsRv.adapter = adapter
    }


    private fun showSourcesInTabs(sources: List<SourcesItem?>?) {
        sources?.forEach { item ->
            val tab = viewBinding.tabs.newTab()
            tab.setText(item!!.name)
            tab.setTag(item)
            viewBinding.tabs.addTab(tab)
        }
        viewBinding.tabs.addOnTabSelectedListener(this)
        viewBinding.tabs.getTabAt(0)!!.select()
    }

    private fun showNewsInRecyclerView(articles: List<ArticlesItem?>?) {
        adapter.setData(articles)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val item = tab!!.tag as SourcesItem
        viewModel.getNews(item.id)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        val item = tab!!.tag as SourcesItem
        viewModel.getNews(item.id)
    }

    override fun initializeViewModel(): HomePageViewModel {
        return homPageViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home_page
    }

}