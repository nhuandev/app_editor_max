package com.example.appeditor.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.appeditor.R
import com.example.appeditor.constant.Constant.KEY_SEEN_WELCOME
import com.example.appeditor.constant.Constant.PREFS_NAME
import android.content.Intent
import android.os.Handler
import com.example.appeditor.MainActivity
import com.example.appeditor.constant.Constant.SPLASH_DELAY
import com.example.appeditor.ui.welcome.WelcomeActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val hasSeenWelcome = sharedPref.getBoolean(KEY_SEEN_WELCOME, false)

        Handler(Looper.getMainLooper()).postDelayed({
            if (hasSeenWelcome) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, SPLASH_DELAY)
    }
}