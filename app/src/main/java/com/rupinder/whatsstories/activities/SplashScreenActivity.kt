package com.rupinder.whatsstories.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.rupinder.whatsstories.R

class SplashScreenActivity : AppCompatActivity() {
    var imageView: ImageView? = null
    var textView1: TextView? = null
    var textView2 :TextView? = null
    var top: Animation? = null
    var bottom: Animation? = null
    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 3000// 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)
        imageView = findViewById(R.id.imageView)
        textView1 = findViewById(R.id.textView)
        textView2=findViewById(R.id.textView1)
        top = AnimationUtils.loadAnimation(this, R.anim.top)
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom)

        imageView?.animation = top
        textView1?.animation = bottom
        textView2?.animation = bottom

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())
    }

    companion object {
        private const val SPLASH_SCREEN = 3000
    }
}
