package com.example.weatherappcompose.forecast.presentation

import com.example.weatherappcompose.forecast.domain.models.Country

data class CountryState(
    val countryState: List<Country> = emptyList(),
    val country: Country? = null,
    val isLoading: Boolean = false,
    val error: String = "",
)
