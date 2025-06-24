package com.example.appeditor.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appeditor.databinding.ActivitySignInBinding
import com.example.appeditor.ui.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        initUI()
    }

    private fun initUI() {
        binding.apply {
            btnSignup.setOnClickListener {
                val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(intent)
            }

            btnContinue.setOnClickListener {

            }
        }
    }
}
