package com.example.weatherappcompose.forecast.presentation

import com.example.weatherappcompose.forecast.domain.models.Country

sealed interface ForecastEvent {
    data class GetSevenDayForecast(val lat: Double, val lon: Double): ForecastEvent
    data class DeleteAndInsertCountry(val country: Country): ForecastEvent
    data class InitialScreen(val lat: Double?, val lon: Double?): ForecastEvent
}