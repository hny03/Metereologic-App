package com.example.metereologic_app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDataResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherData>,
    val city: City
)

@Serializable
data class WeatherData(
    val dt: Long,
    val main: MainWeather,
    val weather: List<WeatherDescription>,
    val clouds: Cloudsf,
    val wind: Windf,
    val visibility: Int,
    val pop: Double,
    val rain: Rainf? = null,
    val sys: Sysf,
    val dt_txt: String
)

@Serializable
data class MainWeather(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Double
)

@Serializable
data class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class Cloudsf(
    val all: Int
)

@Serializable
data class Windf(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

@Serializable
data class Rainf(
    @SerialName("3h") val threeHour: Double
)

@Serializable
data class Sysf(
    val pod: String
)

@Serializable
data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)