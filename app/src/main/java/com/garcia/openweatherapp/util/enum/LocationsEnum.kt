package com.garcia.openweatherapp.util.enum
import com.garcia.openweatherapp.R

enum class LocationsEnum(val title: String,val drawableId: Int) {
    MONTEVIDEO("Montevideo", R.drawable.montevideo),
    LONDRES("Londres", R.drawable.london),
    SAN_PABLO("San Pablo", R.drawable.sau_paolo),
    BUENOS_AIRES("Buenos Aires", R.drawable.buenos_aires),
    MUNICH("Munich", R.drawable.berlin),
    CURRENT_LOCATION("Mi ubicación", R.drawable.location),
}