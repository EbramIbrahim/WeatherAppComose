package com.example.weatherappcompose.forecast.domain.models

data class ForecastInfo(
    val weatherDataPerDay : Map<Int, List<Forecast>>,
    val currentWeatherData: Forecast?
)
