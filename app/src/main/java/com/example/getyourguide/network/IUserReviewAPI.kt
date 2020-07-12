package com.example.getyourguide.network

import com.example.getyourguide.network.model.UserRatingResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IUserReviewAPI {

    @GET("activities/{listingId}/reviews")
    fun getUserRating(@Path("listingId") listingId: String,
                      @Query("limit") limit: Int,
                      @Query("offset") offset: Int,
                      @Query("sort") sortBy: String): Single<Response<UserRatingResponse>>
}