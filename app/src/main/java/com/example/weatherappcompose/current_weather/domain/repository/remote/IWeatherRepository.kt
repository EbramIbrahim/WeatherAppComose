package com.example.weatherappcompose.current_weather.domain.repository.remote

import com.example.weatherappcompose.current_weather.domain.models.Country
import com.example.weatherappcompose.current_weather.domain.models.ForecastInfo

interface IWeatherRepository {

    suspend fun getCountry(query: String): List<Country>
    suspend fun getForecast(latitude: Double, longitude: Double): ForecastInfo
}