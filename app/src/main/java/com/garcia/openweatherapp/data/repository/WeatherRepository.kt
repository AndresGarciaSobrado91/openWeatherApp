package com.garcia.openweatherapp.data.repository

import com.garcia.openweatherapp.data.remote.api.ApiService
import com.garcia.openweatherapp.model.CurrentWeather
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val apiService: ApiService
){

    suspend fun getCurrentWeatherByCityName(cityName: String): Response<CurrentWeather> {
        return apiService.getCurrentWeatherByCityName(cityName)
    }

    suspend fun getCurrentWeatherByCoord(lat: Double, lon: Double): Response<CurrentWeather> {
        return apiService.getCurrentWeatherByCoord(lat,lon)
    }

}