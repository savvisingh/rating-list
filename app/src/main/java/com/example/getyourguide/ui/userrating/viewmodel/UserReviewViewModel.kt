package com.example.getyourguide.ui.userrating.viewmodel

import com.example.getyourguide.R
import com.example.getyourguide.base.BaseViewModel
import com.example.getyourguide.network.model.UserRating
import com.example.getyourguide.utils.DateUtils

class UserReviewViewModel(rating: UserRating) : BaseViewModel(R.layout.item_user_rating){
    val message by lazy{
        rating.message
    }

    val enjoyment by lazy {
        rating.enjoyment
    }

    val date by lazy {
        DateUtils.convertDate(rating.created)
    }

    val rating by lazy {
        rating.rating
    }

    val imageUrl by lazy {
        rating.author.photo
    }

    val userInfo by lazy {
        rating.author.fullName + " - " + rating.author.country
    }
}