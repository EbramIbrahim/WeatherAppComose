package com.example.weatherappcompose.forecast.domain.mapper

import com.example.weatherappcompose.forecast.data.dto.ForecastDto
import com.example.weatherappcompose.forecast.domain.models.Forecast
import com.example.weatherappcompose.forecast.domain.models.ForecastInfo
import com.example.weatherappcompose.forecast.domain.models.IndexedForecastData
import com.example.weatherappcompose.forecast.domain.models.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun ForecastDto.toForecast(): Map<Int, List<Forecast>> {
    return hourly.time.mapIndexed { index, time ->
        val temperature = hourly.temperature_2m[index]
        val windSpeeds = hourly.wind_speed_10m[index]
        val humidity = hourly.relative_humidity_2m[index]
        IndexedForecastData(
            index = index,
            data = Forecast(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                windSpeed = windSpeeds,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(6)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map {
            it.data
        }
    }
}

fun ForecastDto.toForecastInfo() : ForecastInfo {
    val weatherDataMap = toForecast()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return ForecastInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}