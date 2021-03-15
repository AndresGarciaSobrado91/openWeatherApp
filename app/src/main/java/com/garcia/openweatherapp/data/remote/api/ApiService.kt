package com.garcia.openweatherapp.data.remote.api

import com.garcia.openweatherapp.model.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather?units=metric")
    suspend fun getCurrentWeatherByCityName(@Query("q") cityName: String): Response<CurrentWeather>

    @GET("weather?units=metric")
    suspend fun getCurrentWeatherByCoord(@Query("lat") lat: Double,@Query("lon") lon: Double ): Response<CurrentWeather>

    companion object {
        const val API_URL = "https://api.openweathermap.org/data/2.5/"
    }
}