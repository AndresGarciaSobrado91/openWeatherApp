package com.garcia.openweatherapp.domain

import com.garcia.openweatherapp.data.repository.WeatherRepository
import com.garcia.openweatherapp.model.CurrentWeather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class GetDefaultWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : MediatorUseCase<GetDefaultWeatherUseCaseParams, GetDefaultWeatherUseCaseResult>() {

    override fun execute(parameters: GetDefaultWeatherUseCaseParams) {
        result.postValue(GetDefaultWeatherUseCaseResult.Loading)
        when (parameters) {
            is GetDefaultWeatherUseCaseParams.GetByCityName -> getWeatherByCityName(parameters)
            is GetDefaultWeatherUseCaseParams.GetByCoord -> getWeatherByCoord(parameters)
        }
    }

    private fun getWeatherByCoord(parameters: GetDefaultWeatherUseCaseParams.GetByCoord) {
        parameters.coroutineScope.launch {
            withContext(parameters.dispatcher) {
                getWeatherByCoord(parameters.lat, parameters.long)
            }
        }
    }

    private suspend fun getWeatherByCoord(lat: Double, lon: Double) {
        try {
            val response = weatherRepository.getCurrentWeatherByCoord(lat,lon)
            if (response.code() == 200)
                result.postValue(GetDefaultWeatherUseCaseResult.Result(response.body()))
            else
                result.postValue(GetDefaultWeatherUseCaseResult.Error(response.message()))
        } catch (ex: Exception) {
            result.postValue(GetDefaultWeatherUseCaseResult.Error(ex.message))
        }
    }

    private fun getWeatherByCityName(parameters: GetDefaultWeatherUseCaseParams.GetByCityName) {
        parameters.coroutineScope.launch {
            withContext(parameters.dispatcher) {
                getWeatherByCityName(parameters.cityName)
            }
        }
    }

    private suspend fun getWeatherByCityName(cityName: String) {
        try {
            val response = weatherRepository.getCurrentWeatherByCityName(cityName)
            if (response.code() == 200)
                result.postValue(GetDefaultWeatherUseCaseResult.Result(response.body()))
            else
                result.postValue(GetDefaultWeatherUseCaseResult.Error(response.message()))
        } catch (ex: Exception) {
            result.postValue(GetDefaultWeatherUseCaseResult.Error(ex.message))
        }
    }
}

sealed class GetDefaultWeatherUseCaseParams {
    class GetByCityName(
        val coroutineScope: CoroutineScope,
        val dispatcher: CoroutineDispatcher,
        val cityName: String,
    ) : GetDefaultWeatherUseCaseParams()

    class GetByCoord(
        val coroutineScope: CoroutineScope,
        val dispatcher: CoroutineDispatcher,
        val lat: Double,
        val long: Double,
    ) : GetDefaultWeatherUseCaseParams()
}

sealed class GetDefaultWeatherUseCaseResult {
    object Loading : GetDefaultWeatherUseCaseResult()
    data class Result(val weather: CurrentWeather?) : GetDefaultWeatherUseCaseResult()
    data class Error(val message: String?) : GetDefaultWeatherUseCaseResult()
}