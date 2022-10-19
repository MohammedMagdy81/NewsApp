package com.example.newsapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.example.newsapp.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val uri = intent.getStringExtra("uri")
        setUpWebView(uri)
    }

    private fun setUpWebView(uri: String?) {
        binding.webView.apply {
            webViewClient= WebViewClient()
            loadUrl(uri?:"")
        }
        val setting = binding.webView.settings
        setUpUrlClicking(setting)
    }

    private fun setUpUrlClicking(setting: WebSettings) {
        setting.apply {
            javaScriptEnabled=true
            setAppCacheEnabled(true)
            cacheMode=WebSettings.LOAD_DEFAULT
            setSupportZoom(false)
            builtInZoomControls= true
            displayZoomControls=true
            textZoom=100
            blockNetworkImage=false
            loadsImagesAutomatically=true
        }
    }


}