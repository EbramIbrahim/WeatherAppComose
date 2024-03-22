package com.example.weatherappcompose.current_weather.domain.repository.local

import com.example.weatherappcompose.current_weather.data.local.CountryEntity
import com.example.weatherappcompose.current_weather.domain.models.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking

class FakeCountryRepository: ICountryRepository {

    val countryItems = mutableListOf<Country>()
    val countryState = MutableStateFlow<List<Country>>(countryItems)

    private fun emitIntoFlow() {
        runBlocking {
            countryState.emit(countryItems)
        }
    }

    override suspend fun insertCountry(country: Country) {
        countryItems.add(country)
        emitIntoFlow()
    }

    override suspend fun deleteCountry() {
        countryItems.removeFirst()
    }

    override fun getCountry(): Flow<CountryEntity> {
       TODO()
    }
}