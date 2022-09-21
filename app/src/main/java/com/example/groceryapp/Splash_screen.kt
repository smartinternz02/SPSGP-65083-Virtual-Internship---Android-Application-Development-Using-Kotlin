package com.example.groceryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar!!.hide()
        Handler().postDelayed(object : Runnable {
            /* class com.prateek.realnews.splashscreen.AnonymousClass1 */
            public override fun run() {
                this@Splash_screen.startActivity(Intent(this@Splash_screen, MainActivity::class.java))
                finish()
            }
        }, 2000)
    }
}