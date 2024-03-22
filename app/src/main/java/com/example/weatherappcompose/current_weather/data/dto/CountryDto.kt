package com.example.weatherappcompose.current_weather.data.dto

data class CountryDto(
    val generationtime_ms: Double,
    val results: List<Result>
)