package com.winda.cuacaku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

// Ubah kode cuaca WMO menjadi teks + emoji
fun weatherDescription(code: Int): String = when (code) {
    0 -> "☀️ Cerah"
    1, 2 -> "🌤️ Cerah berawan"
    3 -> "☁️ Berawan"
    45, 48 -> "🌫️ Berkabut"
    51, 53, 55 -> "🌦️ Gerimis"
    61, 63, 65 -> "🌧️ Hujan"
    80, 81, 82 -> "🌧️ Hujan lebat"
    95, 96, 99 -> "⛈️ Badai petir"
    else -> "🌡️ Tidak diketahui"
}

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    var city by remember { mutableStateOf("Surakarta") }
    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))

        Text("Cuacaku", fontSize = 32.sp, style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Nama kota") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = { viewModel.searchWeather(city) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cari Cuaca")
        }

        Spacer(Modifier.height(32.dp))

        when {
            state.isLoading -> CircularProgressIndicator()

            state.error != null -> Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )

            state.weather != null -> WeatherCard(state.cityName, state.weather)
        }
    }
}

@Composable
fun WeatherCard(cityName: String, weather: CurrentWeather) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(cityName, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            Text(weatherDescription(weather.weatherCode), fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))
            Text("${weather.temperature}°C", fontSize = 56.sp)
            Text("Terasa seperti ${weather.feelsLike}°C")
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("💧 ${weather.humidity}%")
                Text("💨 ${weather.windSpeed} km/j")
            }
        }
    }
}