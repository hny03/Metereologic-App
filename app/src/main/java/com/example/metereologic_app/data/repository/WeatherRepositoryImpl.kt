package com.example.metereologic_app.data.repository

import com.example.metereologic_app.data.model.WeatherForecast
import com.example.metereologic_app.data.model.WeatherInfo
import com.example.metereologic_app.data.remote.RemoteDataSource
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Float, lng: Float): WeatherInfo {
        val responseWeather = remoteDataSource.getWeatherDataResponse(lat, lng)
        val weather = responseWeather.weather[0]

        val responseForecast = remoteDataSource.getForecastDataResponse(lat, lng)

        return WeatherInfo(
            coord = responseWeather.coord,
            locationName = responseWeather.name,
            conditionIcon = weather.icon,
            condition = weather.main,
            temperature = responseWeather.main.temp.roundToInt(),
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            isDay = weather.icon.last() == 'd',
            feelsLike = responseWeather.main.feelsLike.roundToInt(),
            windSpeed = responseWeather.wind.speed,
            windDirection = responseWeather.wind.deg,
            sunriseTime = responseWeather.sys?.sunrise ?: 0L,
            sunsetTime = responseWeather.sys?.sunset ?: 0L,
            visibilityMeters = responseWeather.visibility,
            forecasts = responseForecast.forecastList?.map { forecast ->
                WeatherForecast(
                    timestamp = forecast.timestamp,
                    dateTimeText = forecast.dateTimeText,
                    temperature = forecast.temperatureData.currentTemp,
                    feelsLike = forecast.temperatureData.feelsLike,
                    minTemp = forecast.temperatureData.minTemp,
                    maxTemp = forecast.temperatureData.maxTemp,
                    condition = forecast.weatherConditions.firstOrNull()?.category ?: "Desconhecido",
                    conditionIcon = forecast.weatherConditions.firstOrNull()?.iconId ?: "",
                    windSpeed = forecast.windData.windSpeed,
                    windDirection = forecast.windData.windDirection,
                    visibility = forecast.visibilityMeters,
                    precipitationProbability = forecast.precipitationProbability,
                    rainfallVolume = forecast.rainfall?.rainVolumeLast3h ?: 0.0,
                    snowfallVolume = forecast.snowfall?.snowVolumeLast3h ?: 0.0
                )
            } ?: emptyList()

        )
    }
}