package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserVisitResponse(
    val visit_date: String,
    val duration: Int?,
    val landmark_name: String,
    val city_name: String
)

@Serializable
data class AuditLogResponse(
    val id: Int,
    val timestamp: String,
    val action: String,
    val details: String,
    val username: String,
    val email: String
)

@Serializable
data class ReviewRequest(
    val landmark_id: Int,
    val rating: Int,
    val comment: String? = null
)

@Serializable
data class VisitRequest(
    val landmark_id: Int,
    val duration: Int
)

@Serializable
data class UserStatisticsResponse(
    val user_id: Int,
    val username: String,
    val favourites_count: Int,
    val visits_count: Int,
    val reviews_count: Int,
    val average_rating_given: Double?,
    val total_visit_duration_minutes: Int
)