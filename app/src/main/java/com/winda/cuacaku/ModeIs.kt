package com.winda.cuacaku

import com.google.gson.annotations.SerializedName

// Respons pencarian kota
data class GeocodingResponse(
    val results: List<GeoResult>?
)

data class GeoResult(
    val name: String,
    val country: String?,
    val latitude: Double,
    val longitude: Double
)

// Respons cuaca
data class WeatherResponse(
    val current: CurrentWeather
)

data class CurrentWeather(
    @SerializedName("temperature_2m") val temperature: Double,
    @SerializedName("relative_humidity_2m") val humidity: Int,
    @SerializedName("apparent_temperature") val feelsLike: Double,
    @SerializedName("wind_speed_10m") val windSpeed: Double,
    @SerializedName("weather_code") val weatherCode: Int
)