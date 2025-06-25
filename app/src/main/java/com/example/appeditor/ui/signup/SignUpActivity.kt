package com.example.appeditor.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appeditor.MainActivity
import com.example.appeditor.R
import com.example.appeditor.constant.Constant
import com.example.appeditor.data.GoogleAuthManager
import com.example.appeditor.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private lateinit var googleAuthManager: GoogleAuthManager
    private val viewModel: AuthViewModel by viewModels()

    private val googleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            googleAuthManager.handleSignInResult(
                data = result.data,
                onSuccess = { credential ->
                    viewModel.signInWithGoogleCredential(credential)
                },
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

        googleAuthManager = GoogleAuthManager(this)
        observeUser()

        binding.btnGoogle.setOnClickListener {
            googleAuthManager.signOut {
                googleAuthManager.launchSignIn(googleLauncher)
            }
        }
    }


    private fun observeUser() {
        viewModel.loading.observe(this) {
            if (it) binding.progressCircular.visibility = View.VISIBLE
            else binding.progressCircular.visibility = View.GONE
        }

        viewModel.user.observe(this) { user ->
            if (user != null) {
                Toast.makeText(
                    this,
                    getString(R.string.toast_google_login_success, user.email),
                    Toast.LENGTH_SHORT
                )
                    .show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(Constant.ARG_EMAIL, user.email)
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
