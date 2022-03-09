package com.example.newsapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.newsapp.R
import com.example.newsapp.ui.home.HomeActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val backgroundImage: ImageView = findViewById(R.id.splash_image)
        val slideAnimation = AnimationUtils.loadAnimation(this,R.anim.myanim)
        backgroundImage.startAnimation(slideAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
                  goToHomeActivity()
        },3000)
    }

    private fun goToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}