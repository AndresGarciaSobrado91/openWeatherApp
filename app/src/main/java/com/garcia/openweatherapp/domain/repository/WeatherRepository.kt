package com.garcia.openweatherapp.domain.repository

import com.garcia.openweatherapp.domain.model.CurrentWeather
import retrofit2.Response

interface WeatherRepository {

    suspend fun getCurrentWeatherByCityName(cityName: String): Response<CurrentWeather>

    suspend fun getCurrentWeatherByCoord(lat: Double, lon: Double): Response<CurrentWeather>
}