package com.example.tmdb.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalHttpUrl: HttpUrl = originalRequest.url
        val httpUrl: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", ApiConstants.API_KEY)
            .build()
        val builder: Request.Builder = originalRequest.newBuilder()
            .url(httpUrl)
        return chain.proceed(builder.build())
    }
}