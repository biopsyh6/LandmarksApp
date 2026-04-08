package com.example.data.remote.api

import com.example.data.model.FavouriteLandmarkResponse
import com.example.data.model.ReviewRequest
import com.example.data.model.UserStatisticsResponse
import com.example.data.model.UserVisitResponse
import com.example.data.model.VisitRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("user/visits/{userId}")
    suspend fun getUserVisits(@Path("userId") userId: Int): List<UserVisitResponse>

    @POST("favourite")
    suspend fun addToFavourite(@Body request: Map<String, Int>): Response<Unit>

    @DELETE("favourite")
    suspend fun removeFromFavourite(@Body request: Map<String, Int>): Response<Unit>

    @POST("review")
    suspend fun addReview(@Body request: ReviewRequest): Response<Unit>

    @POST("visit")
    suspend fun recordVisit(@Body request: VisitRequest): Response<Unit>

    @GET("favourites/{userId}")
    suspend fun getUserFavourites(@Path("userId") userId: Int): List<FavouriteLandmarkResponse>

    @GET("user/statistics/{userId}")
    suspend fun getUserStatistics(@Path("userId") userId: Int): UserStatisticsResponse
}