package com.example.hacksample.utils.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        Log.e("@@@","requestUrl is ${originalRequest.url}")
        return chain.proceed(request)
    }
}