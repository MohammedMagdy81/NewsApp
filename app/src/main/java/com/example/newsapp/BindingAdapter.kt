package com.example.newsapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("urlToImage")
fun setImageFromUrl(imageView: ImageView,url:String){
    Glide.with(imageView)
        .load(url)
        .placeholder(com.google.android.material.R.drawable.mtrl_ic_error)
        .into(imageView)
}