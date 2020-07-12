package com.example.getyourguide.utils.paging

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@IntDef(
    PageLoadingStatus.NOT_STARTED,
    PageLoadingStatus.LOADING,
    PageLoadingStatus.LOADED,
    PageLoadingStatus.END_REACHED,
    PageLoadingStatus.FAILED,
    PageLoadingStatus.TERMINATED
)
@Retention(RetentionPolicy.SOURCE)
annotation class PageLoadingStatus {
    companion object {
        const val NOT_STARTED = 0
        const val LOADING = 1
        const val LOADED = 2
        const val END_REACHED = 3
        const val FAILED = 4
        const val TERMINATED = 5
    }
}