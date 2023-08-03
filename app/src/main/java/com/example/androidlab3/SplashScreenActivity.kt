package com.example.androidlab3

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000

    private var loadingTextView: TextView? = null
    private val handler = Handler()
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        loadingTextView = findViewById(R.id.loadingTextView)
        runnable = object : Runnable {
            var dotCount = 0
            override fun run() {
                dotCount++
                if (dotCount > 3) {
                    dotCount = 1
                }
                var dots = ""
                for (i in 0 until dotCount) {
                    dots += "."
                }
                loadingTextView?.text = "Loading$dots"
                handler.postDelayed(this, 500)
            }
        }
        handler.postDelayed(runnable!!, 500)
        Handler().postDelayed({
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable!!)
    }
}
