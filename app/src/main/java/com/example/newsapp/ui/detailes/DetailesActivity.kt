package com.example.newsapp.ui.detailes


import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsapp.Constants
import com.example.newsapp.R
import com.example.newsapp.api.model.ArticlesItem
import com.example.newsapp.databinding.ActivityDetailesBinding
import com.example.newsapp.localdata.NewsDataBase
import com.example.newsapp.localdata.NewsModel
import com.example.todoapp.base.BaseActivity

class DetailesActivity : BaseActivity<DetailesViewModel,ActivityDetailesBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var uri= intent.getStringExtra(Constants.URI_KEY)
        setUpWebView(uri!!)

    }

    private fun setUpWebView(uri:String) {
        viewBinding.webView?.apply {
            webViewClient= WebViewClient()
            uri.let {
                loadUrl(it)
            }
            val setting = viewBinding.webView.settings
            setUpUrlClicking(setting)

        }
    }

    private fun setUpUrlClicking(setting: WebSettings) {
        setting.apply {
            javaScriptEnabled=true
            setAppCacheEnabled(true)
            cacheMode= WebSettings.LOAD_DEFAULT
            setSupportZoom(false)
            builtInZoomControls= true
            displayZoomControls=false
            textZoom=100
            blockNetworkImage=false
            loadsImagesAutomatically=true
        }
    }


    override fun initializeViewMode(): DetailesViewModel {
        return ViewModelProvider(this).get(DetailesViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_detailes
    }
}