package com.example.appeditor

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.appeditor.databinding.ActivityMainBinding
import com.example.appeditor.ui.splash.SplashRepository
import com.example.appeditor.ui.splash.SplashViewModel

class MainActivity : ComponentActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: SplashViewModel by lazy { SplashViewModel(SplashRepository(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.apply {
            tvEmailUser.text = viewModel.getSaveEdEmail()
        }
    }
}
