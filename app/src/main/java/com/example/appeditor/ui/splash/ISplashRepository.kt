package com.example.appeditor.ui.splash

interface ISplashRepository {
    fun hasScreenWelcome(): Boolean
    fun getSavaEdEmail() : String
}