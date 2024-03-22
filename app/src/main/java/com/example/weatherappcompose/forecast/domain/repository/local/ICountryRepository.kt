package com.example.weatherappcompose.forecast.domain.repository.local

import com.example.weatherappcompose.forecast.data.local.CountryEntity
import com.example.weatherappcompose.forecast.domain.models.Country
import kotlinx.coroutines.flow.Flow

interface ICountryRepository {


    suspend fun insertCountry(country: Country)

    suspend fun deleteCountry()

    fun getCountry(): Flow<CountryEntity>


}