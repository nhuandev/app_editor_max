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
import com.example.appeditor.constant.Constant
import com.example.appeditor.ui.signin.SignInActivity
import com.example.appeditor.ui.welcome.WelcomeActivity
import com.example.appeditor.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val hasSeenWelcome = sharedPref.getBoolean(KEY_SEEN_WELCOME, false)

        val dataLogin = sharedPref.getString(Constant.ARG_EMAIL, "")

        Handler(Looper.getMainLooper()).postDelayed({
            if (hasSeenWelcome) {
                if (dataLogin.isNullOrEmpty()) {
                    val intent = Intent(this@SplashActivity, SignInActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            } else {
                val intent = Intent(this@SplashActivity, WelcomeActivity::class.java)
                startActivity(intent)
            }
        }, Constant.SPLASH_DELAY)
    }
}