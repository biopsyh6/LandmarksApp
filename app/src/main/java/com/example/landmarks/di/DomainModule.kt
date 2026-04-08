package com.example.landmarks.di

import com.example.domain.repository.IAuthRepository
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<LoginUseCase> {
        LoginUseCase(get<IAuthRepository>())
    }

    factory<RegisterUseCase> {
        RegisterUseCase(get<IAuthRepository>())
    }
}