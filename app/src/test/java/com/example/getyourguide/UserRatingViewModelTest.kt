package com.example.getyourguide

import com.example.getyourguide.network.UserReviewManager
import com.example.getyourguide.network.model.Pagination
import com.example.getyourguide.network.model.UserRating
import com.example.getyourguide.network.model.UserRatingResponse
import com.example.getyourguide.ui.userrating.viewmodel.LoadingViewModel
import com.example.getyourguide.ui.userrating.viewmodel.UserRatingsViewModel
import com.example.getyourguide.utils.Constants
import com.example.getyourguide.utils.paging.PageLoadingStatus
import com.example.getyourguide.utils.rxjava.ISchedulers
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class UserRatingViewModelTest {

    lateinit var viewModel: UserRatingsViewModel

    var mockedUserReviewManager: UserReviewManager = mock()

    var mockedSchedulers: ISchedulers = mock()

    var emptyRatingResponse = UserRatingResponse(0, 0.0, Pagination(0, 0))

    var successResponse = UserRatingResponse(1000, 4.0, Pagination(100, 10), getListOfReviews())

    @Before
    fun init(){
        whenever(mockedSchedulers.io()).thenReturn(TestScheduler(500, TimeUnit.MILLISECONDS))
        whenever(mockedSchedulers.ui()).thenReturn(TestScheduler(500, TimeUnit.MILLISECONDS))
        whenever(mockedUserReviewManager.getRatingsForPlace(anyString(), anyInt(), anyInt(), anyString()))
            .thenReturn(Single.just(Response.success(emptyRatingResponse)))

        viewModel = spy(UserRatingsViewModel(mockedUserReviewManager, mockedSchedulers))
    }


    @Test
    fun testSortByClickAction(){
        assertEquals(viewModel.sortBy, Constants.SORT_BY_DESC)
        viewModel.sortFilterClick()

        assertEquals(viewModel.sortBy, Constants.SORT_BY_ASC)
        verify(viewModel).fetchRatings()
    }

    @Test
    fun setOffsetTest(){
        viewModel.setOffset(10)

        assertEquals(viewModel.offsetTillNow, 10)
        assertEquals(viewModel.mOffsetLoaded.get(), 9)
    }

    @Test
    fun `fetchRatings with offset 0`(){

        viewModel.fetchRatings(0)

        assertEquals(viewModel.offsetTillNow, 0)
        assertEquals(viewModel.mOffsetLoaded.get(), 0)
        assertEquals(viewModel.ratings.size, 1)
        assert(viewModel.ratings[0] is LoadingViewModel)
        assertEquals(viewModel.mNewPageLoadStatus.get(), PageLoadingStatus.LOADING)

        verify(mockedUserReviewManager).getRatingsForPlace(anyString(), anyInt(), anyInt(), anyString())
    }

    @Test
    fun `fetchRatings Success`(){
        whenever(mockedSchedulers.io()).thenReturn(Schedulers.trampoline())
        whenever(mockedSchedulers.ui()).thenReturn(Schedulers.trampoline())
        whenever(mockedUserReviewManager.getRatingsForPlace(anyString(), anyInt(), anyInt(), anyString()))
            .thenReturn(Single.just(Response.success(successResponse)))

        viewModel.fetchRatings(0)
        verify(mockedUserReviewManager).getRatingsForPlace(anyString(), anyInt(), anyInt(), anyString())

        assertEquals(viewModel.mTotalOffset.get(), 1000)
        assertEquals(viewModel.mNewPageLoadStatus.get(), PageLoadingStatus.LOADED)
        assertEquals(viewModel.ratings.size, 5)
        assertEquals(viewModel.offsetTillNow, 5)
        assertEquals(viewModel.mOffsetLoaded.get(), 4)

    }

    fun getListOfReviews(): List<UserRating>{
        val list = ArrayList<UserRating>()
        list.add(UserRating())
        list.add(UserRating())
        list.add(UserRating())
        list.add(UserRating())
        list.add(UserRating())
        return list
    }

}