package com.example.newsapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("urlToImage")
fun setImageFromUrl(imageView: ImageView,url:String?){
    Glide.with(imageView)
        .load(url)
        .into(imageView)
}