package com.example.appeditor.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.appeditor.R
import android.content.Intent
import android.os.Handler
import com.example.appeditor.utils.Constant
import com.example.appeditor.ui.auth.SignInActivity
import com.example.appeditor.ui.welcome.WelcomeActivity
import com.example.appeditor.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by lazy { SplashViewModel(SplashRepository(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initUI()
    }

    private fun initUI() {
        Handler(Looper.getMainLooper()).postDelayed({
            when {
                !viewModel.hasScreenWelcome() -> {
                    startActivity(Intent(this, WelcomeActivity::class.java))
                }

                !viewModel.getSaveEdEmail().isNullOrEmpty() -> {
                    startActivity(Intent(this, MainActivity::class.java).apply {
                        putExtra(Constant.ARG_EMAIL, viewModel.getSaveEdEmail())
                    })
                }

                else -> {
                    startActivity(Intent(this, SignInActivity::class.java))
                }
            }
            finish()
        }, Constant.SPLASH_DELAY)
    }


}
