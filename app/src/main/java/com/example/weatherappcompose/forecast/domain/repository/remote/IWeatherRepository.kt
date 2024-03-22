package com.example.weatherappcompose.forecast.domain.repository.remote

import com.example.weatherappcompose.forecast.domain.models.Country
import com.example.weatherappcompose.forecast.domain.models.ForecastInfo

interface IWeatherRepository {

    suspend fun getCountry(query: String): List<Country>
    suspend fun getForecast(latitude: Double, longitude: Double): ForecastInfo
}