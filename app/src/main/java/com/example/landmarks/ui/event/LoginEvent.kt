package com.example.landmarks.ui.event

sealed interface LoginEvent {
    data class ShowToast(val message: String) : LoginEvent
    data object NavigateToMain : LoginEvent
}