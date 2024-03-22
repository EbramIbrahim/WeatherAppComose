package com.example.weatherappcompose.core.presentation

import com.example.weatherappcompose.current_weather.domain.models.ForecastInfo

data class ForecastState(
    val forecast: ForecastInfo? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
