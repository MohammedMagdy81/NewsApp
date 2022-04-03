package com.example.newsapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.example.newsapp.R
import com.example.newsapp.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    lateinit var lottieAnimationView: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lottieAnimationView =findViewById(R.id.lottie);
        lottieAnimationView.animate().translationY(1500F).setDuration(1000).setStartDelay(5000);


        Handler(Looper.getMainLooper()).postDelayed({
                  goToHomeActivity()
        },3000)
    }

    private fun goToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        finish()
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}