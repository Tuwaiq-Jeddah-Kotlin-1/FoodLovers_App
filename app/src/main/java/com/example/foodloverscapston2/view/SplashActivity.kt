package com.example.foodloverscapston2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.example.foodloverscapston2.R

class SplashActivity : AppCompatActivity() {
   private lateinit var  tvAnim : TextView
   private lateinit var tvAnim1: TextView
   private lateinit var anim : Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        tvAnim = findViewById(R.id.tvAnim)
        tvAnim1 = findViewById(R.id.tvAnim1)

        tvAnim.animation = anim
        tvAnim1.animation = anim

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            //code
            startActivity(Intent(this, MainActivity::class.java))
        }, 2000)
    }
}