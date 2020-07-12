package com.example.getyourguide.network

import com.example.getyourguide.network.model.UserRatingResponse
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class UserReviewManager @Inject constructor(private var api: IUserReviewAPI){
    fun getRatingsForPlace(placeId: String, limit: Int, offset: Int, sortBy: String) : Single<Response<UserRatingResponse>> {
        return api.getUserRating(placeId, limit, offset, sortBy)
    }
}