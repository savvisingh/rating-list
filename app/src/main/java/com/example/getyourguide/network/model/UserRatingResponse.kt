package com.example.getyourguide.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRatingResponse(val totalCount: Int,
                              val averageRating: Double,
                              val pagination: Pagination,
                              @SerialName("reviews") val reviews: List<UserRating> = listOf())