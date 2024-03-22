package com.example.weatherappcompose.core.presentation

import com.example.weatherappcompose.current_weather.domain.models.Country

data class CountryState(
    val countryState: List<Country> = emptyList(),
    val country: Country? = null,
    val isLoading: Boolean = false,
    val error: String = "",
)
