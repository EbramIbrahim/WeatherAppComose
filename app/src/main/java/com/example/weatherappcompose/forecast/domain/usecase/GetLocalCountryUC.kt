package com.example.weatherappcompose.forecast.domain.usecase

import com.example.weatherappcompose.core.common.Resource
import com.example.weatherappcompose.forecast.data.repository.local.CountryRepositoryImp
import com.example.weatherappcompose.forecast.domain.mapper.CountryMapper
import com.example.weatherappcompose.forecast.domain.models.Country
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocalCountryUC @Inject constructor(
    private val countryRepositoryImp: CountryRepositoryImp
) {

    operator fun invoke(): Flow<Resource<Country>> = channelFlow {
        try {
            send(Resource.Loading())
            countryRepositoryImp.getCountry().collectLatest {
                send(Resource.Success(CountryMapper.entityToDomain(it)))
            }
        } catch (e: Exception) {
            send(Resource.Error(e.message ?: ""))
        }
    }
}