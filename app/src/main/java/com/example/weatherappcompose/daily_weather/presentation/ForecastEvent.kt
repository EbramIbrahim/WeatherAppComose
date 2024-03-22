package com.example.weatherappcompose.daily_weather.presentation

import com.example.weatherappcompose.current_weather.domain.models.Country

sealed interface ForecastEvent {
    data class GetSevenDayForecast(val lat: Double, val lon: Double): ForecastEvent
    data class DeleteAndInsertCountry(val country: Country): ForecastEvent
    data object InitialScreen: ForecastEvent
}