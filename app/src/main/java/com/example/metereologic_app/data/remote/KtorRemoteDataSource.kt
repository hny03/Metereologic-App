package com.example.metereologic_app.data.remote

import com.example.metereologic_app.data.remote.response.ForecastDataResponse
import com.example.metereologic_app.data.remote.response.WeatherDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class KtorRemoteDataSource @Inject constructor(
    private val httpClient: HttpClient
) : RemoteDataSource {

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
        private const val API_KEY = "APIKEY"
    }

    override suspend fun getWeatherDataResponse(lat: Float, lng: Float): WeatherDataResponse {
        return httpClient
            .get("$BASE_URL/weather?lat=$lat&lon=$lng&appid=$API_KEY&units=metric&lang=pt_br")
            .body()
    }

    override suspend fun getWeatherByCity(city: String): WeatherDataResponse {
        return httpClient
            .get("$BASE_URL/weather?q=$city&appid=$API_KEY&units=metric&lang=pt_br")
            .body()
    }

    override suspend fun getForecastDataResponse(lat: Float, lng: Float): ForecastDataResponse {
        return httpClient
            .get("$BASE_URL/weather?lat=$lat&lon=$lng&appid=$API_KEY&units=metric&lang=pt_br")
            .body()
    }

    override suspend fun getForecastByCity(city: String): ForecastDataResponse {
        return httpClient
            .get("$BASE_URL/weather?q=$city&appid=$API_KEY&units=metric&lang=pt_br")
            .body()
    }

    // Adicionar outra funçao suspend(colocar na interface q implementa tambem para o forecast
}
