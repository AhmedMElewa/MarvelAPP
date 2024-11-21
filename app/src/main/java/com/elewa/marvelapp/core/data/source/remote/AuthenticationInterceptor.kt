package com.elewa.marvelapp.core.data.source.remote

import com.elewa.marvelapp.BuildConfig
import com.elewa.marvelapp.core.data.constants.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalHttpUrl: HttpUrl = originalRequest.url
        val newHttpUrl: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("hash", Constants.generateMd5Hash())
            .build()
        val newRequest: Request = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()
        return chain.proceed(newRequest)
    }
}