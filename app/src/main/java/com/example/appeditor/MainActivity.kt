package com.example.appeditor

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.appeditor.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
