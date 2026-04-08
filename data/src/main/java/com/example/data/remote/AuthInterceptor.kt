package com.example.data.remote

import com.example.data.local.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        val isPublic = when {
            path.contains("/auth/login") -> true
            path.contains("/auth/register") -> true
            path.contains("/nearby") -> true
            path.contains("/categories") -> true
            path.startsWith("/landmark/") && request.method == "GET" -> true
            path.startsWith("/top/landmarks") -> true
            else -> false
        }
        if (isPublic) {
            return chain.proceed(request)
        }

        val token = tokenManager.getAccessTokenSync()
        val authenticatedRequest = if (!token.isNullOrBlank()) {
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            request
        }
        return chain.proceed(authenticatedRequest)
    }
}