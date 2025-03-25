package com.example.metereologic_app.data.model

// Data class do clima para a interface grafica, mudaremos as variaveis de acordo
// com a necessidade da interface grafica e dos dados que sao disponibilizados na api
data class WeatherInfo(
    val locationName: String,
    val conditionIcon: String,
    val condition: String,
    val temperature: Int,
    val dayOfWeek: String,
    val isDay: Boolean,
)