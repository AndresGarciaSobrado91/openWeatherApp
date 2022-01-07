package com.garcia.openweatherapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.garcia.openweatherapp.domain.GetDefaultWeatherUseCase
import com.garcia.openweatherapp.domain.GetDefaultWeatherUseCaseParams
import com.garcia.openweatherapp.domain.GetDefaultWeatherUseCaseResult
import com.garcia.openweatherapp.domain.model.Coord
import com.garcia.openweatherapp.domain.model.CurrentWeather
import com.garcia.openweatherapp.domain.model.Temperature
import com.garcia.openweatherapp.ui.base.ScopedViewModel
import com.garcia.openweatherapp.ui.fragment.WeatherFragmentArgs
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getDefaultWeatherUseCase: GetDefaultWeatherUseCase,
) : ScopedViewModel() {

    private var _getWeatherResultObservable = getDefaultWeatherUseCase.observe()
    val weatherResult : LiveData<GetDefaultWeatherUseCaseResult>
        get() = _getWeatherResultObservable

    private val _weatherObservable = MutableLiveData<CurrentWeather>()
    val weatherObservable : LiveData<CurrentWeather>
        get() = _weatherObservable

    val temperature = Transformations.map(weatherObservable) { value ->
        Temperature(value.main.temp.toInt(),value.main.temp_max.toInt(),value.main.temp_min.toInt())
    }

    private fun getWeatherByCity(cityName : String){
        getDefaultWeatherUseCase.execute(
                GetDefaultWeatherUseCaseParams.GetByCityName(
                        coroutineScope,
                        Dispatchers.IO,
                        cityName
                )
        )
    }

    private fun getWeatherByCoord(coord : Coord){
        getDefaultWeatherUseCase.execute(
            GetDefaultWeatherUseCaseParams.GetByCoord(
                coroutineScope,
                Dispatchers.IO,
                coord.lat,
                coord.lon
            )
        )
    }

    fun onViewCreated(args: WeatherFragmentArgs){
        if (!args.cityName.isNullOrEmpty())
            getWeatherByCity(args.cityName)
        else if (args.coord != null)
            getWeatherByCoord(args.coord)
    }

    fun setWeather(weather : CurrentWeather){
        _weatherObservable.value = weather
    }
}