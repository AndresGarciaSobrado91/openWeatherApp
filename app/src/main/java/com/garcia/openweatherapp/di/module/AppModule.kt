package com.garcia.openweatherapp.di.module

import android.content.Context
import com.garcia.openweatherapp.MyApp
import com.garcia.openweatherapp.data.remote.api.ApiService
import com.garcia.openweatherapp.data.repository.WeatherRepositoryImpl
import com.garcia.openweatherapp.domain.GetDefaultWeatherUseCase
import com.garcia.openweatherapp.util.ui.LoadingViewManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    open fun provideContext(application: MyApp): Context = application

    @Provides
    @Singleton
    open fun provideWeatherRepository(
        apiService: ApiService
    ): WeatherRepositoryImpl = WeatherRepositoryImpl(apiService)

    @Singleton
    @Provides
    open fun provideLoadingViewManager(): LoadingViewManager = LoadingViewManager()

    @Provides
    open fun provideGetDefaultWeatherUseCase(
        weatherRepository: WeatherRepositoryImpl
    ): GetDefaultWeatherUseCase = GetDefaultWeatherUseCase(weatherRepository)
}