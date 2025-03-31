package com.example.metereologic_app.data.remote

import com.example.metereologic_app.data.remote.response.ForecastDataResponse
import com.example.metereologic_app.data.remote.response.WeatherDataResponse

interface RemoteDataSource {

    suspend fun getWeatherDataResponse(lat: Double, lng: Double): WeatherDataResponse

    suspend fun getWeatherByCity(city: String): WeatherDataResponse

    suspend fun getForecastDataResponse(lat: Double, lng: Double): ForecastDataResponse

    suspend fun getForecastByCity(city: String): ForecastDataResponse

}