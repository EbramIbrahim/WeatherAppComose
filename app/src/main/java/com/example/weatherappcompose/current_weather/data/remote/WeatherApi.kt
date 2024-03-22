package com.example.weatherappcompose.current_weather.data.remote

import com.example.weatherappcompose.current_weather.data.dto.ForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {



    @GET("forecast?current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m,weather_code")
    suspend fun getWeatherData(
        @Query("latitude") lat : Double,
        @Query("longitude") long : Double
    ): ForecastDto
}