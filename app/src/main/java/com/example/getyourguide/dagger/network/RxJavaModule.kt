package com.example.getyourguide.dagger.network

import com.example.getyourguide.utils.rxjava.ISchedulers
import com.example.getyourguide.utils.rxjava.Schedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RxJavaModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideSchedulers(schedulers: Schedulers) : ISchedulers{
        return schedulers
    }
}