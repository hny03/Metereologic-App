package com.example.metereologic_app.data.repository

import com.example.metereologic_app.data.model.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Float, lng: Float): WeatherInfo
}