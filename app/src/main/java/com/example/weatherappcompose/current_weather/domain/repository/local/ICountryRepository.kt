package com.example.weatherappcompose.current_weather.domain.repository.local

import com.example.weatherappcompose.current_weather.data.local.CountryEntity
import com.example.weatherappcompose.current_weather.domain.models.Country
import kotlinx.coroutines.flow.Flow

interface ICountryRepository {


    suspend fun insertCountry(country: Country)

    suspend fun deleteCountry()

    fun getCountry(): Flow<CountryEntity>


}