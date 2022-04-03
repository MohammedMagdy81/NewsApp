package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemNewsListBinding
import com.example.newsapp.localdata.NewsDiffUtil
import com.example.newsapp.localdata.NewsModel

class NewsListAdapter (var mNewsItem:List<NewsModel?>?, private var onItemClick : OnItemClick?) : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>(){

    class NewsListViewHolder(val itemNewsBinding : ItemNewsListBinding):RecyclerView.ViewHolder(itemNewsBinding.root){
        fun bind(newsModel: NewsModel){
            itemNewsBinding.newsModel= newsModel
        }
    }


    interface OnItemClick{
        fun onDeleteItem(newsModel: NewsModel)
        fun onClickedItem(newsModel: NewsModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val itemNewsBinding : ItemNewsListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_news_list,parent,false)
        return NewsListViewHolder(itemNewsBinding)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        var newsItem= mNewsItem!![position]
        holder.bind(newsItem!!)
        if (onItemClick!=null){
            holder.itemNewsBinding.itemDelete.setOnClickListener {
                onItemClick!!.onDeleteItem(newsItem)
            }
            holder.itemNewsBinding.root.setOnClickListener {
                onItemClick!!.onClickedItem(newsItem)
            }
        }

    }
    fun changeData(newsList:List<NewsModel?>?){
        var diffRes= DiffUtil.calculateDiff(NewsDiffUtil(mNewsItem,newsList))
        mNewsItem= newsList
        diffRes.dispatchUpdatesTo(this)
       // notifyDataSetChanged()
    }
    fun setData(mNewsItem:List<NewsModel?>?){
        this.mNewsItem= mNewsItem
        notifyDataSetChanged()
    }

    override fun getItemCount() = mNewsItem!!.size?:0
}