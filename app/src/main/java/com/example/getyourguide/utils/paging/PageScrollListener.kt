package com.example.getyourguide.utils.paging

import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PageScrollListener(private val mLinearLayoutManager: LinearLayoutManager,
                         paginationListener: IPaginationListener?, visibleThreshold: Int = 0) : RecyclerView.OnScrollListener() {
    // The minimum amount of items to have below your current
    // scroll position before isLoading more.
    private var mVisibleThreshold = 0
    private var mFirstVisibleItem = 0
    private var mVisibleItemCount = 0
    private var mTotalItemCount = 0
    private var mCurrentPage = -1
    private var mLastPage = UNDEFINED

    @PageLoadingStatus
    private var mNextPageLoadingStatus: Int = PageLoadingStatus.NOT_STARTED
    private var mPaginationListener: IPaginationListener? = null

    private val mHandler: Handler? = Handler(Handler.Callback { msg ->
        when (msg.what) {
            MESSAGE_LOAD_NEXT_PAGE -> mPaginationListener?.let {
                it.loadNewPage(mCurrentPage + 1)
            }
        }
        true
    })

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        mVisibleItemCount = recyclerView.childCount
        mTotalItemCount = mLinearLayoutManager.itemCount
        mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
        validateCurrentPage()
        if (mTotalItemCount - mVisibleItemCount <= mFirstVisibleItem + mVisibleThreshold) {

            //Check if there are no more pages to load
            if (mLastPage == -1 || mCurrentPage == mLastPage && mLastPage != UNDEFINED) {
                //Do Nothing...Since thy User has reached end
            } else {
                //Since the user hasn't reached the end(known or unknown) we'll have to check following cases..
                when (mNextPageLoadingStatus) {
                    PageLoadingStatus.TERMINATED, PageLoadingStatus.FAILED, PageLoadingStatus.NOT_STARTED -> {

                        //Ask Pagination listener to loadNextPage
                        Log.d(TAG, "onScrolled: Ask Pagination listener to loadNextPage")
                        if (mHandler != null) {
                            mHandler.removeMessages(MESSAGE_LOAD_NEXT_PAGE)
                            val loadNextPageMessage = Message.obtain()
                            loadNextPageMessage.what =
                                MESSAGE_LOAD_NEXT_PAGE
                            mNextPageLoadingStatus = PageLoadingStatus.LOADING
                            mHandler.sendMessage(loadNextPageMessage)
                        }
                    }
                    PageLoadingStatus.LOADING ->
                        //Since Pagination listener is still loading ...Do Nothing
                        Log.d(TAG, "onScrolled: Since Pagination listener is still loading ...Do Nothing")
                }
            }
        }
    }

    fun reset() {
        Log.d(TAG, "reset() called")
        mVisibleItemCount = 0
        mTotalItemCount = 0
        mFirstVisibleItem = 0
        mNextPageLoadingStatus = PageLoadingStatus.NOT_STARTED
        mCurrentPage = -1
        mLastPage = UNDEFINED
    }

    fun onPageLoaded(page: Int) {
        Log.d(TAG, "onPageLoaded() called with: page = [$page]")
        mCurrentPage = page
        mNextPageLoadingStatus = PageLoadingStatus.NOT_STARTED
    }

    fun updateLastPage(lastPage: Int) {
        Log.d(TAG, "updateLastPage() called with: lastPage = [$lastPage]")
        mLastPage = lastPage
    }

    fun updateNextPageLoadingStatus(@PageLoadingStatus status: Int) {
        Log.d(TAG, "updateNextPageLoadingStatus: $status")
        mNextPageLoadingStatus = status
        if (status == PageLoadingStatus.LOADED) {
            onPageLoaded(mCurrentPage + 1)
        } else if (status == PageLoadingStatus.END_REACHED) {
            mCurrentPage = mCurrentPage + 1
            mLastPage = mCurrentPage
        }
    }

    private fun validateCurrentPage() {
        if (mCurrentPage > mLastPage && mLastPage != UNDEFINED) {
            mCurrentPage = mLastPage
        }
    }

    companion object {
        const val TAG = "PageScrollListener"
        const val MESSAGE_LOAD_NEXT_PAGE = 1
        const val UNDEFINED = -100
    }

    init {
        mVisibleThreshold = visibleThreshold
        mPaginationListener = paginationListener
    }
}
