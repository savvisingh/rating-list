package com.example.getyourguide.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Pagination(val limit: Int,
                      val offset: Int)