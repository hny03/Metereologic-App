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

    override suspend fun getWeatherData(lat: Double, lng: Double): WeatherInfo {
        val responseWeather = remoteDataSource.getWeatherDataResponse(lat, lng)
        val weather = responseWeather.weather[0]

        val responseForecast = remoteDataSource.getForecastDataResponse(lat, lng)
        val forecasts = responseForecast.list.map { weatherData ->
            WeatherForecast(
                dateTimeText = weatherData.dt_txt,
                temperature = weatherData.main.temp.toInt(),
                condition = weatherData.weather.firstOrNull()?.main ?: "Unknown",
                conditionIcon = weatherData.weather.firstOrNull()?.icon ?: ""
            )
        }



        return WeatherInfo(

            locationName = responseWeather.name,
            conditionIcon = weather.icon,
            condition = weather.main,
            temperature = responseWeather.main.temp.roundToInt(),
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(
                TextStyle.FULL,
                Locale.getDefault()
            ),
            isDay = weather.icon.last() == 'd',
            feelsLike = responseWeather.main.feelsLike.roundToInt(),
            windSpeed = responseWeather.wind.speed,
            windDirection = responseWeather.wind.deg,
            sunriseTime = responseWeather.sys?.sunrise ?: 0L,
            sunsetTime = responseWeather.sys?.sunset ?: 0L,
            visibilityMeters = responseWeather.visibility,
            coord = responseWeather.coord,
            forecasts = forecasts,
            humidity = responseWeather.main.humidity,
            tempMin = responseWeather.main.tempMin.toInt(),
            tempMax = responseWeather.main.tempMax.toInt()
        )
    }

    override suspend fun getWeatherCity(city: String): WeatherInfo {
        val responseWeather = remoteDataSource.getWeatherByCity(city)
        val weather = responseWeather.weather[0]

        val responseForecast = remoteDataSource.getForecastByCity(city)
        val forecasts = responseForecast.list.map { weatherData ->
            WeatherForecast(
                dateTimeText = weatherData.dt_txt,
                temperature = weatherData.main.temp.toInt(),
                condition = weatherData.weather.firstOrNull()?.main ?: "Unknown",
                conditionIcon = weatherData.weather.firstOrNull()?.icon ?: ""
            )
        }



        return WeatherInfo(

            locationName = responseWeather.name,
            conditionIcon = weather.icon,
            condition = weather.main,
            temperature = responseWeather.main.temp.roundToInt(),
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(
                TextStyle.FULL,
                Locale.getDefault()
            ),
            isDay = weather.icon.last() == 'd',
            feelsLike = responseWeather.main.feelsLike.roundToInt(),
            windSpeed = responseWeather.wind.speed,
            windDirection = responseWeather.wind.deg,
            sunriseTime = responseWeather.sys?.sunrise ?: 0L,
            sunsetTime = responseWeather.sys?.sunset ?: 0L,
            visibilityMeters = responseWeather.visibility,
            coord = responseWeather.coord,
            forecasts = forecasts,
            humidity = responseWeather.main.humidity,
            tempMin = responseWeather.main.tempMin.toInt(),
            tempMax = responseWeather.main.tempMax.toInt()
        )
    }


}