package com.example.weatherappcompose.forecast.presentation

import com.example.weatherappcompose.forecast.domain.models.Country
import com.example.weatherappcompose.forecast.domain.models.ForecastInfo

data class ForecastState(
    val forecast: ForecastInfo? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
