package com.example.metereologic_app.ui.feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metereologic_app.data.repository.WeatherRepository
import com.example.metereologic_app.data.room.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    var city by mutableStateOf("")

    fun onCityChange(newCity: String) {
        city = newCity
    }

    fun getWeather() {
        viewModelScope.launch {
            if (city.isNotEmpty()) {
                getWeatherUseCase.execute(city)
            }
        }
    }

    private val _weatherInfoState = MutableStateFlow(WeatherInfoState())
    val weatherInfoState: StateFlow<WeatherInfoState> = _weatherInfoState.asStateFlow()

    init {
        getWeatherInfo()
    }

    private fun getWeatherInfo() {
        viewModelScope.launch {
            // Mudar as coordenadas para o local do usuario
            val weatherInfo = weatherRepository.getWeatherData(-18.9186, -48.2772)
            _weatherInfoState.update {
                it.copy(weatherInfo = weatherInfo)
            }
        }
    }
}