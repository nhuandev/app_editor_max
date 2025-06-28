package com.example.appeditor.ui.splash

import android.content.Context
import com.example.appeditor.utils.Constant

class SplashRepository(private val context: Context) : ISplashRepository {
    private val sharedPreferences = context.getSharedPreferences(Constant.PREFS_NAME, Context.MODE_PRIVATE)

    override fun hasScreenWelcome(): Boolean  {
        return sharedPreferences.getBoolean(Constant.KEY_SEEN_WELCOME, false)
    }

    override fun getSavaEdEmail(): String {
        return sharedPreferences.getString(Constant.ARG_EMAIL, "") ?: ""
    }
}