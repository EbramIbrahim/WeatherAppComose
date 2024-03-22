package com.example.weatherappcompose.current_weather.domain.models

data class ForecastInfo(
    val weatherDataPerDay : Map<Int, List<Forecast>>,
    val currentWeatherData: Forecast?
)
