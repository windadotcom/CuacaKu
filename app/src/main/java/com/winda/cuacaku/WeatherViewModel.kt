package com.winda.cuacaku

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class WeatherUiState(
    val isLoading: Boolean = false,
    val cityName: String = "",
    val weather: CurrentWeather? = null,
    val error: String? = null
)

class WeatherViewModel : ViewModel() {

    var uiState by mutableStateOf(WeatherUiState())
        private set

    fun searchWeather(city: String) {
        if (city.isBlank()) {
            uiState = WeatherUiState(error = "Nama kota tidak boleh kosong")
            return
        }

        viewModelScope.launch {
            uiState = WeatherUiState(isLoading = true)
            try {
                val geo = RetrofitInstance.api.searchCity(city.trim())
                val place = geo.results?.firstOrNull()

                if (place == null) {
                    uiState = WeatherUiState(error = "Kota \"$city\" tidak ditemukan")
                    return@launch
                }

                val result = RetrofitInstance.api.getWeather(place.latitude, place.longitude)
                uiState = WeatherUiState(
                    cityName = listOfNotNull(place.name, place.country).joinToString(", "),
                    weather = result.current
                )
            } catch (e: Exception) {
                uiState = WeatherUiState(error = "Gagal memuat data. Periksa koneksi internet.")
            }
        }
    }
}