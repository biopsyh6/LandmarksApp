package com.example.data.mapper.exception

import android.net.http.HttpException
import com.example.domain.model.exception.AuthExceptionDomainModel
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException

fun Throwable.toAuthExceptionDomainModel(): AuthExceptionDomainModel {
    return when (this) {
        is UnknownHostException, is ConnectException, is IOException ->
            AuthExceptionDomainModel.NoInternet(this)

        is retrofit2.HttpException -> {
            when (code()) {
                401 -> AuthExceptionDomainModel.Unauthorized(this)
                409 -> AuthExceptionDomainModel.Conflict(this)
                else -> AuthExceptionDomainModel.ServerError(this)
            }
        }

        is AuthExceptionDomainModel -> this

        else -> AuthExceptionDomainModel.Other(this)
    }
}