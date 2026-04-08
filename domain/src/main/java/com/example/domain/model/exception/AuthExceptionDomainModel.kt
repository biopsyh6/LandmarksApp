package com.example.domain.model.exception

sealed class AuthExceptionDomainModel(exception: Throwable) : Throwable(exception) {
    override val cause: Throwable = exception

    data class NoInternet(val exception: Throwable) : AuthExceptionDomainModel(exception)
    data class Unauthorized(val exception: Throwable) : AuthExceptionDomainModel(exception)
    data class Conflict(val exception: Throwable) : AuthExceptionDomainModel(exception) // email exists
    data class ServerError(val exception: Throwable) : AuthExceptionDomainModel(exception)
    data class Other(val exception: Throwable) : AuthExceptionDomainModel(exception)
}