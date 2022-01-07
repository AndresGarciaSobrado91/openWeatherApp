package com.garcia.openweatherapp.domain.model

import java.io.Serializable

data class Coord(
    val lat: Double,
    val lon: Double
) : Serializable