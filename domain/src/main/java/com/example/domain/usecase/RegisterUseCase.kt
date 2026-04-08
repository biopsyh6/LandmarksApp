package com.example.domain.usecase

import com.example.domain.TResult
import com.example.domain.model.AuthDomainModel
import com.example.domain.model.RegisterParams
import com.example.domain.model.exception.AuthExceptionDomainModel
import com.example.domain.repository.IAuthRepository

class RegisterUseCase(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String
    ): TResult<AuthDomainModel, AuthExceptionDomainModel> {
        return authRepository.register(RegisterParams(username, email, password))
    }
}