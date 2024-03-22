package com.example.weatherappcompose.current_weather.domain.mapper

import com.example.weatherappcompose.core.domain.mapper.ForecastMapper
import com.example.weatherappcompose.current_weather.data.dto.ForecastDto
import com.example.weatherappcompose.current_weather.domain.models.Forecast
import com.example.weatherappcompose.current_weather.domain.models.ForecastInfo
import com.example.weatherappcompose.current_weather.domain.models.IndexedForecastData
import com.example.weatherappcompose.current_weather.domain.models.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object ForecastMapperImpl: ForecastMapper<Forecast, ForecastDto, ForecastInfo> {

    override fun dtoToDomain(dto: ForecastDto): Map<Int, List<Forecast>> {
        return dto.hourly.time.mapIndexed { index, time ->
            val temperature = dto.hourly.temperature_2m[index]
            val windSpeeds = dto.hourly.wind_speed_10m[index]
            val humidity = dto.hourly.relative_humidity_2m[index]
            val weatherCode = dto.hourly.weather_code[index]
            IndexedForecastData(
                index = index,
                data = Forecast(
                    time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                    temperatureCelsius = temperature,
                    windSpeed = windSpeeds,
                    humidity = humidity,
                    weatherType = WeatherType.fromWMO(weatherCode)
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

    override fun dtoToInfo(dto: ForecastDto): ForecastInfo {
        val weatherDataMap = dtoToDomain(dto)
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
}