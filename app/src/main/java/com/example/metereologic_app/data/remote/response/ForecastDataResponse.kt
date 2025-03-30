package com.example.metereologic_app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDataResponse(
    val statusCode: String? = null, // Tornar opcional
    val statusMessage: String? = null, // Tornar opcional e ajustar o tipo
    val count: Int? = null, // Tornar opcional
    val forecastList: List<ForecastData>? = null, // Tornar opcional
    val cityInfo: CityInfo? = null // Tornar opcional
)

@Serializable
data class ForecastData(
    val timestamp: Long,
    val temperatureData: TemperatureData,
    val weatherConditions: List<WeatherCondition>,
    val cloudCoverage: CloudCoverage,
    val windData: WindData,
    val visibilityMeters: Int,
    val precipitationProbability: Double,
    val rainfall: Rainfall? = null,
    val snowfall: Snowfall? = null,
    val systemData: SystemData,
    @SerialName("dateTimeText") val dateTimeText: String
)

@Serializable
data class TemperatureData(
    val currentTemp: Double,
    @SerialName("feelsLike") val feelsLike: Double,
    @SerialName("minTemp") val minTemp: Double,
    @SerialName("maxTemp") val maxTemp: Double,
    val atmosphericPressure: Int,
    val humidityPercentage: Int,
    @SerialName("seaLevelPressure") val seaLevelPressure: Int? = null,
    @SerialName("groundLevelPressure") val groundLevelPressure: Int? = null
)

@Serializable
data class WeatherCondition(
    val conditionId: Int,
    val category: String,
    val description: String,
    val iconId: String
)

@Serializable
data class CloudCoverage(
    val percentage: Int
)

@Serializable
data class WindData(
    val windSpeed: Double,
    val windDirection: Int,
    val windGust: Double? = null
)

@Serializable
data class Rainfall(
    @SerialName("rainVolumeLast3h") val rainVolumeLast3h: Double? = null
)

@Serializable
data class Snowfall(
    @SerialName("snowVolumeLast3h") val snowVolumeLast3h: Double? = null
)

@Serializable
data class SystemData(
    val timeOfDay: String
)

@Serializable
data class CityInfo(
    val cityId: Long,
    val cityName: String,
    val coordinates: Coordinates,
    val countryCode: String,
    val populationSize: Int,
    val utcOffset: Int,
    val sunriseTime: Long,
    val sunsetTime: Long
)

@Serializable
data class Coordinates(
    val latitude: Double,
    val longitude: Double
)
