package com.raven.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    private val apiKey = "f4zL4tULmghgTFeT1A4HrZ8rzeKbzKj"
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        var url = originalHttpUrl.newBuilder()
            .addQueryParameter("api-key",apiKey)
            .build()
        val requestBuilder = originalRequest.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}