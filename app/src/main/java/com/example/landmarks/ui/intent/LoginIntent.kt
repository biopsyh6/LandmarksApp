package com.example.landmarks.ui.intent

sealed interface LoginIntent {
    data class UpdateEmail(val email: String) : LoginIntent
    data class UpdatePassword(val password: String) : LoginIntent
    data object Submit : LoginIntent
}