package com.example.domain.repository

import com.example.domain.TResult
import com.example.domain.model.AuthDomainModel
import com.example.domain.model.LoginParams
import com.example.domain.model.RegisterParams
import com.example.domain.model.exception.AuthExceptionDomainModel

interface IAuthRepository {
    suspend fun register(params: RegisterParams): TResult<AuthDomainModel, AuthExceptionDomainModel>
    suspend fun login(params: LoginParams): TResult<AuthDomainModel, AuthExceptionDomainModel>
}