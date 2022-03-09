package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.graphics.translationMatrix
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.databinding.NewsItemBinding

class NewsAdapter(var newsList:List<ArticlesItem?>?):RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val newsItemBinding:NewsItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.news_item,parent,false)
        return NewsViewHolder(newsItemBinding)
       //val view= LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        //return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem= newsList!![position]
        holder.bind(newsItem!!)
//        holder.apply {
//            date.text=newsItem?.publishedAt
//            title.text=newsItem?.title
           // desc.text=newsItem?.description
            Glide.with(holder.itemView)
                .load(newsItem?.urlToImage)
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .into(holder.newsItemBinding.image )
        }



    fun changeData(newsList: List<ArticlesItem?>?){
        this.newsList=newsList
        notifyDataSetChanged()
    }

    override fun getItemCount() = newsList?.size?:0

    class NewsViewHolder(val newsItemBinding:NewsItemBinding):RecyclerView.ViewHolder(newsItemBinding.root){
//        val date : TextView = itemView.findViewById(R.id.date)
//        val title : TextView = itemView.findViewById(R.id.title)
//        val desc : TextView = itemView.findViewById(R.id.description)
//        val image : ImageView = itemView.findViewById(R.id.image)
//        val progress : ProgressBar = itemView.findViewById(R.id.news_item_progress)
        fun bind (newsItem:ArticlesItem){
            newsItemBinding.newsItem= newsItem
        }
    }


}