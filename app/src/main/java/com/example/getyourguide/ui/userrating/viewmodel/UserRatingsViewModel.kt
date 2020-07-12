package com.example.getyourguide.ui.userrating.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.getyourguide.R
import com.example.getyourguide.base.BaseViewModel
import com.example.getyourguide.network.UserReviewManager
import com.example.getyourguide.network.model.UserRatingResponse
import com.example.getyourguide.utils.Constants
import com.example.getyourguide.utils.paging.IPaginationListener
import com.example.getyourguide.utils.paging.PageLoadingStatus
import com.example.getyourguide.utils.rxjava.ISchedulers
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response
import javax.inject.Inject

class UserRatingsViewModel
@Inject
constructor(var userReviewManager: UserReviewManager,
            var schedulers: ISchedulers) : ViewModel() {

    var compositeDisposable = CompositeDisposable()
    var ratings = ObservableArrayList<BaseViewModel>()
    var mTotalOffset = ObservableInt(0)
    var mNewPageLoadStatus = ObservableInt(PageLoadingStatus.NOT_STARTED)
    var mOffsetLoaded = ObservableInt(0)
    var sortByIcon = ObservableInt(R.drawable.ic_baseline_arrow_drop_down_24)
    var offsetTillNow = 0
    var sortBy = Constants.SORT_BY_DESC

    fun fetchRatings(offset: Int = 0){
        if(offset == 0){
            ratings.clear()
            offsetTillNow = 0
            mOffsetLoaded.set(0)
        }

        ratings.add(LoadingViewModel())
        mNewPageLoadStatus.set(PageLoadingStatus.LOADING)
        compositeDisposable.add(userReviewManager.getRatingsForPlace("23776", 10, offset, sortBy)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                handleResponse(it)
            }, {
                    e -> e.printStackTrace()
                ratings.removeAt(ratings.lastIndex)
                mNewPageLoadStatus.set(PageLoadingStatus.FAILED)
            }))
    }


    private fun handleResponse(it: Response<UserRatingResponse>) {
        if(it.isSuccessful){
            val data = mutableListOf<BaseViewModel>()
            mNewPageLoadStatus.set(PageLoadingStatus.LOADED)
            it.body()?.let {
                it.reviews.forEach {
                    data.add(UserReviewViewModel(it))
                }
                mTotalOffset.set(it.totalCount)
                setOffset(offsetTillNow + data.size)
            }

            ratings.removeAt(ratings.lastIndex)
            if (data.isNotEmpty()) {
                ratings.addAll(data)
            } else {
                mNewPageLoadStatus.set(PageLoadingStatus.END_REACHED)
            }
        }else{
            mNewPageLoadStatus.set(PageLoadingStatus.FAILED)
            ratings.removeAt(ratings.lastIndex)
        }
    }

    var mPaginationListener = object : IPaginationListener{
        override fun loadNewPage(pageNum: Int) {
            mNewPageLoadStatus.set(PageLoadingStatus.LOADING)
            fetchRatings(pageNum)
        }
    }

    fun setOffset(offset: Int){
        offsetTillNow = offset
        if(offset > 0){
            mOffsetLoaded.set(offset-1)
        } else {
            mOffsetLoaded.set(0)
        }
    }

    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }

    fun sortFilterClick() {
        if(sortBy.equals(Constants.SORT_BY_DESC, true)){
            sortBy = Constants.SORT_BY_ASC
            sortByIcon.set(R.drawable.ic_baseline_arrow_drop_up_24)
        }else {
            sortBy = Constants.SORT_BY_DESC
            sortByIcon.set(R.drawable.ic_baseline_arrow_drop_down_24)
        }
        fetchRatings()
    }


}