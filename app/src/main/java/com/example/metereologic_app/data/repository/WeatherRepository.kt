package com.example.metereologic_app.data.repository

import com.example.metereologic_app.data.model.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, lng: Double): WeatherInfo

    suspend fun getWeatherCity(city: String): WeatherInfo
}