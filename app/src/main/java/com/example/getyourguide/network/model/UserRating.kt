package com.example.getyourguide.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRating(val id: Int = 0,
                      val title: String = "",
                      val rating: Int = 0,
                      val message: String = "",
                      val enjoyment: String = "",
                      val language: String = "",
                      val travelerType: String = "",
                      val created: String = "",
                      val author: Author = Author(),
                      val isAnonymous: Boolean = true)