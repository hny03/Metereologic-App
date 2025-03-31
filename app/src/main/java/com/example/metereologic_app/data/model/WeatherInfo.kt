package com.example.metereologic_app.data.model

import com.example.metereologic_app.data.remote.response.Coord
import kotlinx.serialization.Serializable

// Data class do clima para a interface grafica, mudaremos as variaveis de acordo
// com a necessidade da interface grafica e dos dados que sao disponibilizados na api
@Serializable
data class WeatherInfo(
    val coord: Coord,
    val locationName: String,
    val conditionIcon: String,
    val condition: String,
    val temperature: Int,
    val dayOfWeek: String,
    val isDay: Boolean,
    val feelsLike: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val sunriseTime: Long,
    val sunsetTime: Long,
    val visibilityMeters: Int,
    val humidity: Int,
    val tempMin: Int,
    val tempMax: Int,
    val forecasts: List<WeatherForecast>
)

@Serializable
data class WeatherForecast(
    val dateTimeText: String,
    val temperature: Int,
    val condition: String,
    val conditionIcon: String,
)
