package com.example.metereologic_app.data.remote

import com.example.metereologic_app.data.remote.response.ForecastDataResponse
import com.example.metereologic_app.data.remote.response.WeatherDataResponse

interface RemoteDataSource {

    suspend fun getWeatherDataResponse(lat: Float, lng: Float): WeatherDataResponse

    suspend fun getWeatherByCity(city: String): WeatherDataResponse

    suspend fun getForecastDataResponse(lat: Float, lng: Float): ForecastDataResponse

    suspend fun getForecastByCity(city: String): ForecastDataResponse

}