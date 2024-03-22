package com.example.weatherappcompose.current_weather.domain.models

data class Country(
    val countryName: String,
    val timeZone: String,
    val latitude: Double,
    val longitude: Double,
    val town: String
)
