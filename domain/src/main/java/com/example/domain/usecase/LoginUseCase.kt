package com.example.domain.usecase

import com.example.domain.TResult
import com.example.domain.model.AuthDomainModel
import com.example.domain.model.LoginParams
import com.example.domain.model.exception.AuthExceptionDomainModel
import com.example.domain.repository.IAuthRepository

class LoginUseCase(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): TResult<AuthDomainModel, AuthExceptionDomainModel> {
        return authRepository.login(LoginParams(email, password))
    }
}