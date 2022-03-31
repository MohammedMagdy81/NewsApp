package com.example.newsapp.ui.detailes

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsapp.Constants
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityDetailesBinding
import com.example.newsapp.localdata.NewsDataBase
import com.example.newsapp.localdata.NewsModel
import com.example.todoapp.base.BaseActivity

class DetailesActivity : BaseActivity<DetailesViewModel,ActivityDetailesBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seuUpViews()

    }

    private fun seuUpViews() {
        val date = intent.getStringExtra(Constants.DATE_KEY)
        val title = intent.getStringExtra(Constants.TITLY_KEY)
        val desc = intent.getStringExtra(Constants.DESC_KEY)
        val uri = intent.getStringExtra(Constants.IMAGE_KEY)

        viewBinding.date.text= date?.split("T","Z",",").toString()
        viewBinding.title.text= title
        viewBinding.description.text=desc
        Glide.with(viewBinding.image)
            .load(uri)
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .into(viewBinding.image)
    }


    override fun initializeViewMode(): DetailesViewModel {
        return ViewModelProvider(this).get(DetailesViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_detailes
    }
}