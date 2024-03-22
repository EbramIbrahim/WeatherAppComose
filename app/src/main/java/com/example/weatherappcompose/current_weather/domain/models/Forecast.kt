package com.example.weatherappcompose.current_weather.domain.models

import java.time.LocalDateTime

data class Forecast(
    val time : LocalDateTime,
    val temperatureCelsius : Double,
    val windSpeed : Double,
    val humidity : Int,
    val weatherType: WeatherType,
)
