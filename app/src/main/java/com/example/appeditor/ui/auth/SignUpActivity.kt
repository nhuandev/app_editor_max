package com.example.appeditor.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.appeditor.MainActivity
import com.example.appeditor.R
import com.example.appeditor.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }

    private val viewModel: AuthViewModel by lazy { AuthViewModel(AuthRepository(this)) }

    private val googleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            viewModel.handleSignInResult(
                data = result.data,
                onError = { error ->
                    Toast.makeText(
                        this,
                        getString(R.string.toast_google_login_failed, error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        initUI()
        observeUser()
    }

    private fun initUI() {
        binding.apply {
            btnGoogle.setOnClickListener {
                viewModel.launchGoogleSignIn(googleLauncher)
            }
        }
    }

    private fun observeUser() {
        viewModel.loading.observe(this) {
            binding.progressCircular.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.user.observe(this) { user ->
            if (user != null) {
                Toast.makeText(
                    this,
                    getString(R.string.toast_google_login_success, user.email),
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.saveEmail()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.toast_google_login_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
