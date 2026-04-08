package com.example.data.remote.api

import com.example.data.model.AuditLogResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AdminApi {
    @GET("audit")
    suspend fun getAuditLog(): List<AuditLogResponse>
}