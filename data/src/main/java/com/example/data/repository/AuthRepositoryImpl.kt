package com.example.data.repository

import com.example.data.local.TokenManager
import com.example.data.mapper.AuthDataMapper
import com.example.data.mapper.exception.toAuthExceptionDomainModel
import com.example.data.model.LoginRequest
import com.example.data.model.RegisterRequest
import com.example.data.remote.api.AuthApi
import com.example.domain.TResult
import com.example.domain.model.AuthDomainModel
import com.example.domain.model.LoginParams
import com.example.domain.model.RegisterParams
import com.example.domain.model.exception.AuthExceptionDomainModel
import com.example.domain.repository.IAuthRepository
import java.io.IOException

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager
) : IAuthRepository {
    override suspend fun register(params: RegisterParams): TResult<AuthDomainModel, AuthExceptionDomainModel> {
        return try {
            val request = RegisterRequest(params.username, params.email, params.password)
            val response = authApi.register(request)
            tokenManager.saveToken(response.token)
            TResult.Success(AuthDataMapper.toDomainModel(response))
        } catch (e: Throwable) {
            TResult.Error(e.toAuthExceptionDomainModel())
        }
    }

    override suspend fun login(params: LoginParams): TResult<AuthDomainModel, AuthExceptionDomainModel> {
        return try {
            val request = LoginRequest(params.email, params.password)
            val response = authApi.login(request)
            tokenManager.saveToken(response.token)
            TResult.Success(AuthDataMapper.toDomainModel(response))
        } catch (e: Throwable) {
            TResult.Error(e.toAuthExceptionDomainModel())
        }
    }
}