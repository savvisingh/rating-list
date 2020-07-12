package com.example.getyourguide.utils

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.getyourguide.R
import com.example.getyourguide.base.BaseViewModel
import com.example.getyourguide.ui.userrating.adapter.RatingRecyclerAdapter
import com.example.getyourguide.utils.Constants.KEY_NEW_PAGE_LOAD_STATUS
import com.example.getyourguide.utils.Constants.KEY_PAGE_LOADED
import com.example.getyourguide.utils.Constants.KEY_PAGINATION_LISTENER
import com.example.getyourguide.utils.Constants.KEY_TOTAL_PAGES
import com.example.getyourguide.utils.Constants.SCROLL_LISTENER_TAG
import com.example.getyourguide.utils.paging.IPaginationListener
import com.example.getyourguide.utils.paging.PageLoadingStatus
import com.example.getyourguide.utils.paging.PageScrollListener


@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, items: List<BaseViewModel>){
    var adapter = recyclerView.adapter
    if (adapter == null){
        adapter = RatingRecyclerAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }

    (adapter as RatingRecyclerAdapter).setItems(items)
}

@BindingAdapter("pagination_listener")
fun setPaginationListener(recyclerView: RecyclerView, paginationListener: IPaginationListener) {
    if (paginationListener == null) {
        return
    }
    recyclerView.setTag(KEY_PAGINATION_LISTENER, paginationListener)
    if (recyclerView.layoutManager == null) {
        return
    }
    val pageScrollListener = PageScrollListener(recyclerView.layoutManager as LinearLayoutManager, paginationListener)
    recyclerView.addOnScrollListener(pageScrollListener)
    if (recyclerView.getTag(KEY_NEW_PAGE_LOAD_STATUS) != null) {
        pageScrollListener.updateNextPageLoadingStatus(recyclerView.getTag(KEY_NEW_PAGE_LOAD_STATUS) as Int)
    }
    if (recyclerView.getTag(KEY_PAGE_LOADED) != null) {
        pageScrollListener.onPageLoaded(recyclerView.getTag(KEY_PAGE_LOADED) as Int)
    }
    if (recyclerView.getTag(KEY_TOTAL_PAGES) != null) {
        pageScrollListener.updateLastPage(recyclerView.getTag(KEY_TOTAL_PAGES) as Int - 1)
    }
    recyclerView.setTag(SCROLL_LISTENER_TAG, pageScrollListener)
}

@BindingAdapter("new_page_load_status")
fun setNewPageLoadStatus(recyclerView: RecyclerView, @PageLoadingStatus newPageLoadStatus: Int) {
    recyclerView.setTag(KEY_NEW_PAGE_LOAD_STATUS, newPageLoadStatus)
    if (recyclerView.getTag(SCROLL_LISTENER_TAG) == null) {
        return
    }
    val pageScrollListener = recyclerView.getTag(SCROLL_LISTENER_TAG) as PageScrollListener
    pageScrollListener.updateNextPageLoadingStatus(newPageLoadStatus)
}

@BindingAdapter("page_loaded")
fun setPageLoaded(recyclerView: RecyclerView, pageLoaded: Int) {
    recyclerView.setTag(KEY_PAGE_LOADED, pageLoaded)
    if (recyclerView.getTag(SCROLL_LISTENER_TAG) == null) {
        return
    }
    val pageScrollListener = recyclerView.getTag(SCROLL_LISTENER_TAG) as PageScrollListener
    pageScrollListener.onPageLoaded(pageLoaded)
}

@BindingAdapter("total_pages")
fun setTotalPages(recyclerView: RecyclerView, totalPages: Int) {
    recyclerView.setTag(KEY_TOTAL_PAGES, totalPages)
    if (recyclerView.getTag(SCROLL_LISTENER_TAG) == null) {
        return
    }
    val pageScrollListener = recyclerView.getTag(SCROLL_LISTENER_TAG) as PageScrollListener
    pageScrollListener.updateLastPage(totalPages - 1)
}

@BindingAdapter("reset_scroll_listener")
fun resetScrollListener(recyclerView: RecyclerView, toReset: Boolean) {
    if (recyclerView.getTag(SCROLL_LISTENER_TAG) == null) {
        return
    }
    val pageScrollListener = recyclerView.getTag(SCROLL_LISTENER_TAG) as PageScrollListener
    pageScrollListener.reset()
}

@BindingAdapter("rating")
fun setRating(ratingBar: RatingBar, rating: Int){
    ratingBar.rating = rating.toFloat()
}

@BindingAdapter("image_url")
fun setRating(imageView: ImageView, url: String){
    if(url.isNotEmpty()){
        Glide.with(imageView)
            .load(url)
            .circleCrop()
            .into(imageView)
    }else {
        Glide.with(imageView)
            .load(R.drawable.ic_baseline_account_circle_24)
            .circleCrop()
            .into(imageView)
    }
}

@BindingAdapter("visibility")
fun setRating(textView: TextView, visible: Boolean){
    if(!visible){
        textView.visibility = View.GONE
    } else {
        textView.visibility = View.VISIBLE
    }
}

@BindingAdapter("image_icon")
fun setImage(imageView: AppCompatImageView, @DrawableRes drawable: Int){
    imageView.setBackgroundResource(drawable)
}