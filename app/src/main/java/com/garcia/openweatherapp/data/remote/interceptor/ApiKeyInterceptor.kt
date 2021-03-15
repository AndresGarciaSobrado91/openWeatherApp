package com.garcia.openweatherapp.data.remote.interceptor

import com.garcia.openweatherapp.MyApp
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val apiKey = MyApp.API_KEY
        val url = original.url().newBuilder().addQueryParameter("appid", apiKey).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}