package com.garcia.openweatherapp.di.module

import com.garcia.openweatherapp.data.remote.api.ApiService
import com.garcia.openweatherapp.data.remote.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApiKeyInterceptor() : ApiKeyInterceptor = ApiKeyInterceptor()

    @Singleton
    @Provides
    fun provideClient(
        apiKeyInterceptor: ApiKeyInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofitService(
        client: OkHttpClient,
    ): ApiService = Retrofit.Builder()
        .client(client)
        .baseUrl(ApiService.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}