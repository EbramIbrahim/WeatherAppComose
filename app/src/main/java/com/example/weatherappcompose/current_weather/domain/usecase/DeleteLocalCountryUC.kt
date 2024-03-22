package com.example.weatherappcompose.current_weather.domain.usecase

import com.example.weatherappcompose.current_weather.data.repository.local.CountryRepositoryImp
import javax.inject.Inject

class DeleteLocalCountryUC @Inject constructor(
    private val countryRepositoryImp: CountryRepositoryImp
) {

    suspend operator fun invoke() {
        countryRepositoryImp.deleteCountry()
    }
}










