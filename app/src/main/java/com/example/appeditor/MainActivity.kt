package com.example.appeditor

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.appeditor.databinding.ActivityMainBinding
import com.example.appeditor.ui.bitmap.BitmapActivity

class MainActivity : ComponentActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.apply {
            btnBitmap.setOnClickListener {
                val intent = Intent(this@MainActivity, BitmapActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
