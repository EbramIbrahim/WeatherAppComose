package com.example.weatherappcompose.current_weather.domain.usecase

import com.example.weatherappcompose.current_weather.data.repository.local.CountryRepositoryImp
import com.example.weatherappcompose.current_weather.domain.models.Country
import javax.inject.Inject

class InsertLocalCountryUC @Inject constructor(
    private val countryRepositoryImp: CountryRepositoryImp
) {

    suspend operator fun invoke(country: Country) {
        countryRepositoryImp.insertCountry(country)
    }
}








