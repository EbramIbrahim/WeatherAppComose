package com.example.weatherappcompose.forecast.data.dto

data class CountryDto(
    val generationtime_ms: Double,
    val results: List<Result>
)