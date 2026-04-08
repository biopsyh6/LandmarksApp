package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NearbyLandmarkResponse(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val distance_m: Double,
    val average_rating: Double
)

@Serializable
data class LandmarkDetailsResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val model_url: String?,
    val city_name: String,
    val country_name: String,
    val average_rating: Double,
    val visit_count: Int,
    val categories: String,
    val photos: String,
    val primary_photo: String?,
    val recent_reviews: String
)

@Serializable
data class FavouriteLandmarkResponse(
    val landmark_id: Int,
    val landmark_name: String,
    val latitude: Double,
    val longitude: Double,
    val average_rating: Double,
    val visit_count: Int,
    val primary_photo: String?
)

@Serializable
data class TopLandmarkResponse(
    val id: Int,
    val name: String,
    val city_name: String,
    val popularity_score: Double
)