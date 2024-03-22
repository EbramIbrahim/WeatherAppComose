package com.example.weatherappcompose.forecast.data.remote

import com.example.weatherappcompose.forecast.data.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApi {

    @GET("search")
    suspend fun getCountry(
        @Query("name") country: String,
    ): CountryDto
}