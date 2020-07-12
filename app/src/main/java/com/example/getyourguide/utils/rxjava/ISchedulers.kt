package com.example.getyourguide.utils.rxjava

import io.reactivex.Scheduler
interface ISchedulers {
    fun io(): Scheduler?
    fun ui(): Scheduler?
}