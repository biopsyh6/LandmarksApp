package com.example.data.mapper

import com.example.data.model.AuthResponse
import com.example.domain.model.AuthDomainModel

object AuthDataMapper {
    fun toDomainModel(dto: AuthResponse): AuthDomainModel =
        AuthDomainModel(
            userId = dto.user_id,
            username = dto.username,
            token = dto.token
        )
}