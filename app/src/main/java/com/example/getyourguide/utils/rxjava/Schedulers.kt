package com.example.getyourguide.utils.rxjava

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

class Schedulers @Inject constructor(): ISchedulers {
    override fun io(): Scheduler? {
        return Schedulers.io()
    }

    override fun ui(): Scheduler? {
        return AndroidSchedulers.mainThread()
    }
}