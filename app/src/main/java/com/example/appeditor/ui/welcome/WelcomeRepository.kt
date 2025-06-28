package com.example.appeditor.ui.welcome

import android.content.Context
import androidx.core.content.edit
import com.example.appeditor.utils.Constant

class WelcomeRepository(context: Context) : IWelcomeRepository {
    private val sharedPreferences =
        context.getSharedPreferences(Constant.PREFS_NAME, Context.MODE_PRIVATE)

    override fun markWelcome() {
        sharedPreferences.edit {
            putBoolean(Constant.KEY_SEEN_WELCOME, true)
            apply()
        }
    }


}
