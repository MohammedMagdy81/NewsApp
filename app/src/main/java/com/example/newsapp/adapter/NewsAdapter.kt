package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.model.ArticlesItem
import java.text.SimpleDateFormat


class NewsAdapter(var articlesItem: List<ArticlesItem?>?) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val newsItemBinding:NewsItemBinding= DataBindingUtil.
        inflate(LayoutInflater.from(parent.context),R.layout.news_item,parent,false)
        return NewsViewHolder(newsItemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articlesItem!!.get(position))
        if (onArticleItemClick!=null){
            holder.itemView.setOnClickListener {
                onArticleItemClick!!.onItemClick(position, articlesItem!![position])
            }
        }

    }

    override fun getItemCount(): Int {
        return articlesItem?.size?:0
    }

    fun setData(articlesItem: List<ArticlesItem?>?){
        this.articlesItem=articlesItem
        notifyDataSetChanged()
    }

    var onArticleItemClick : OnArticleItemClick?=null
     interface OnArticleItemClick{
        fun onItemClick(position: Int,articlesItem: ArticlesItem?)
    }

    class NewsViewHolder(val newsItemBinding:NewsItemBinding): RecyclerView.ViewHolder(newsItemBinding.root) {

        fun bind(articlesItem: ArticlesItem?) {
            val date = articlesItem!!.publishedAt!!.substring(0,10)
            val time = articlesItem!!.publishedAt!!.substring(11,16)
            newsItemBinding.date.text= date+"  "+ time
           newsItemBinding.newsList=articlesItem
            newsItemBinding.invalidateAll()
        }

    }

}