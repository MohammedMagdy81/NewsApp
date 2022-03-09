package com.example.newsapp.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("urlToImage")
fun setUrlToImage(image:ImageView,url:String){
    Glide.with(image)
        .load(url)
        .error(com.google.android.material.R.drawable.mtrl_ic_error)
        .into(image)
}