package com.example.appeditor

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.appeditor.databinding.ActivityMainBinding
import com.example.appeditor.ui.bitmap.BitmapActivity
import com.example.appeditor.ui.bitmap.CustomDrawableView

class MainActivity : ComponentActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var customDrawableView: CustomDrawableView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.apply {
            tvEmailUser.text = intent.getStringExtra("email")
        }
    }
}
