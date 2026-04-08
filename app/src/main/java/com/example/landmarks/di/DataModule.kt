package com.example.landmarks.di

import com.example.data.local.TokenManager
import com.example.data.remote.AuthInterceptor
import com.example.data.remote.api.AdminApi
import com.example.data.remote.api.AuthApi
import com.example.data.remote.api.LandmarksApi
import com.example.data.remote.api.UserApi
import com.example.data.repository.AuthRepositoryImpl
import com.example.domain.repository.IAuthRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val dataModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            encodeDefaults = true
        }
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single { TokenManager(androidContext()) }

    single<AuthInterceptor> { AuthInterceptor(get()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .client(get<OkHttpClient>())
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single<AdminApi> { get<Retrofit>().create(AdminApi::class.java) }
    single<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }
    single<LandmarksApi> { get<Retrofit>().create(LandmarksApi::class.java) }
    single<UserApi> { get<Retrofit>().create(UserApi::class.java) }

    single<IAuthRepository> {
        AuthRepositoryImpl(
            authApi = get<AuthApi>(),
            tokenManager = get<TokenManager>()
        )
    }

}