package com.example.weatherappcompose.forecast.domain.usecase

import com.example.weatherappcompose.forecast.data.repository.local.CountryRepositoryImp
import javax.inject.Inject

class DeleteLocalCountryUC @Inject constructor(
    private val countryRepositoryImp: CountryRepositoryImp
) {

    suspend operator fun invoke() {
        countryRepositoryImp.deleteCountry()
    }
}










