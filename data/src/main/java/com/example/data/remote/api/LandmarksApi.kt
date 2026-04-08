package com.example.data.remote.api

import com.example.data.model.LandmarkDetailsResponse
import com.example.data.model.NearbyLandmarkResponse
import com.example.data.model.TopLandmarkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LandmarksApi {
    @GET("nearby")
    suspend fun getNearby(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("radius") radius: Int = 10000,
        @Query("limit") limit: Int = 20
    ): List<NearbyLandmarkResponse>

    @GET("landmark/{id}")
    suspend fun getLandmarkDetails(@Path("id") id: Int): LandmarkDetailsResponse

    @GET("top/landmarks")
    suspend fun getTopLandmarks(
        @Query("limit") limit: Int = 10,
    ): List<TopLandmarkResponse>
}