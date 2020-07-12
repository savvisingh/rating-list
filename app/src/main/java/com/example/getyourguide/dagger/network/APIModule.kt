package com.example.getyourguide.dagger.network

import com.example.getyourguide.BuildConfig
import com.example.getyourguide.network.IUserReviewAPI
import com.example.getyourguide.network.interceptor.HeaderInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton


@Module
object APIModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideRetrofit(): Retrofit{

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(HeaderInterceptor())

        val retrofit = Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.nonstrict
                .asConverterFactory(MediaType.get("application/json")))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideAPI(retrofit: Retrofit): IUserReviewAPI{
        return retrofit.create(IUserReviewAPI::class.java)
    }


}