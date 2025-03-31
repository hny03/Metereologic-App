package com.example.metereologic_app.data.room

import android.util.Log
import com.example.metereologic_app.data.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository
) {
    suspend fun execute(cityName: String) {
        try {
            val weatherInfo = weatherRepository.getWeatherCity(cityName) // 🔹 Puxa da API
            val city = City(id = null, name = weatherInfo.locationName) // 🔹 Cria objeto City
            cityRepository.insert(id = city.id, city = city.name) // 🔹 Salva no Room
        } catch (e: Exception) {
            Log.e("GetWeatherUseCase", "Erro ao buscar e salvar cidade: ${e.message}")
        }
    }
}