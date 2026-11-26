package com.winda.cuacaku

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    // Ubah nama kota menjadi koordinat
    @GET("https://geocoding-api.open-meteo.com/v1/search")
    suspend fun searchCity(
        @Query("name") name: String,
        @Query("count") count: Int = 1,
        @Query("language") language: String = "id"
    ): GeocodingResponse

    // Ambil cuaca saat ini berdasarkan koordinat
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String =
            "temperature_2m,relative_humidity_2m,apparent_temperature,wind_speed_10m,weather_code",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse
}

object RetrofitInstance {
    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}