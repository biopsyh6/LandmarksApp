package com.example.domain.model

data class AuthDomainModel(
    val userId: Int,
    val username: String,
    val token: String
)

data class RegisterParams(
    val username: String,
    val email: String,
    val password: String
)

data class LoginParams(
    val email: String,
    val password: String
)
