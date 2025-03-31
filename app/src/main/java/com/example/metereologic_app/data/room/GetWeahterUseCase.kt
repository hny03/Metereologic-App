package com.example.metereologic_app.data.room

/*
import android.util.Log
import com.example.metereologic_app.data.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository
) {
    suspend fun execute(cityName: String) {
        try {
            val weatherInfo = weatherRepository.getWeatherCity(cityName)
            val city = City(id = null, name = weatherInfo.locationName)
            cityRepository.insert(id = city.id, city = city.name)
        } catch (e: Exception) {
            Log.e("GetWeatherUseCase", "Erro ao buscar e salvar cidade: ${e.message}")
        }
    }
}
*/