package com.example.getyourguide.network.interceptor

import com.example.getyourguide.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val userAgentHeaderRequest = originalRequest.newBuilder()
            .removeHeader(Constants.HEADER_USER_AGENT)
            .addHeader(Constants.HEADER_USER_AGENT, Constants.HEADER_VALUE)
            .build()
        return chain.proceed(userAgentHeaderRequest)
    }
}