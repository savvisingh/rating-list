package com.example.getyourguide

import com.example.getyourguide.network.model.Author
import com.example.getyourguide.network.model.UserRating
import com.example.getyourguide.ui.userrating.viewmodel.UserReviewViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class UserReviewViewModelTest {

    lateinit var viewModel: UserReviewViewModel

    private val TEST_TITLE = "testTitle"
    private val TEST_MESSAGE = "testMessage"
    private val TEST_ENJOYMENT = "testEnjoyment"
    private val TEST_NAME = "testName"
    private val TEST_COUNTRY = "testCountry"
    private val TEST_RATING = 4
    private val TEST_IMAGE_URL = "testImageUrl"


    @Test
    fun testInitialization(){
        val rating = UserRating(id = 1000, title = TEST_TITLE, rating = TEST_RATING,
            message = TEST_MESSAGE, enjoyment = TEST_ENJOYMENT,
            author = Author(TEST_NAME, TEST_COUNTRY, TEST_IMAGE_URL))

        viewModel = UserReviewViewModel(rating)

        assertEquals(viewModel.message, TEST_MESSAGE)
        assertEquals(viewModel.rating, TEST_RATING)
        assertEquals(viewModel.enjoyment, TEST_ENJOYMENT)
        assertEquals(viewModel.userInfo, "$TEST_NAME - $TEST_COUNTRY")
        assertEquals(viewModel.imageUrl, TEST_IMAGE_URL)
    }
}