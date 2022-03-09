package com.example.newsapp.localdata

import androidx.recyclerview.widget.DiffUtil

class NewsDiffUtil(var mOldlist :List<NewsModel?>? , var mNewlist :List<NewsModel?>?): DiffUtil.Callback() {
    override fun getOldListSize() = mOldlist!!.size?:0

    override fun getNewListSize() = mNewlist!!.size?:0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return (mOldlist!![oldItemPosition]==mNewlist!![newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean  =true

}