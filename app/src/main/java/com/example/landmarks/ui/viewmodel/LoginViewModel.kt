package com.example.landmarks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.LoginUseCase
import com.example.landmarks.ui.SingleFlowEvent
import com.example.landmarks.ui.event.LoginEvent
import com.example.landmarks.ui.intent.LoginIntent
import com.example.landmarks.ui.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _event = SingleFlowEvent<LoginEvent>(viewModelScope)
    val event = _event.flow

    fun onIntent(intent: LoginIntent) {
        when(intent) {
            is LoginIntent.UpdateEmail -> _state.update { it.copy(email = intent.email) }
            is LoginIntent.UpdatePassword -> _state.update { it.copy(password = intent.password) }
            is LoginIntent.Submit -> performLogin()
        }
    }

    private fun performLogin() {

    }
}