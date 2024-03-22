package com.example.weatherappcompose.forecast.domain.usecase

import com.example.weatherappcompose.core.common.Resource
import com.example.weatherappcompose.forecast.data.repository.local.CountryRepositoryImp
import com.example.weatherappcompose.forecast.domain.models.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertLocalCountryUC @Inject constructor(
    private val countryRepositoryImp: CountryRepositoryImp
) {

    suspend operator fun invoke(country: Country) {
        countryRepositoryImp.insertCountry(country)
    }
}








