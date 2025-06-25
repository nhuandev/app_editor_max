package com.example.appeditor.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthViewModel : ViewModel() {
    private val auth by lazy { FirebaseAuth.getInstance() }

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun signInWithGoogleCredential(credential: AuthCredential) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    auth.signInWithCredential(credential).await()
                }
                _user.value = result.user
            } catch (e: Exception) {
                _user.value = null
            } finally {
                _loading.value = false
            }
        }
    }
}
