package com.garcia.openweatherapp.data.repository

import com.garcia.openweatherapp.data.remote.api.ApiService
import com.garcia.openweatherapp.domain.model.CurrentWeather
import com.garcia.openweatherapp.domain.repository.WeatherRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WeatherRepository {

    override suspend fun getCurrentWeatherByCityName(cityName: String): Response<CurrentWeather> {
        return apiService.getCurrentWeatherByCityName(cityName)
    }

    override suspend fun getCurrentWeatherByCoord(lat: Double, lon: Double): Response<CurrentWeather> {
        return apiService.getCurrentWeatherByCoord(lat,lon)
    }
}