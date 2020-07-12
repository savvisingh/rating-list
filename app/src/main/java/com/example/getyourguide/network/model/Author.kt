package com.example.getyourguide.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Author(val fullName: String = "",
                  val country: String = "",
                  val photo: String = "")