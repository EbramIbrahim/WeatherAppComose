package com.example.weatherappcompose.current_weather.domain.usecase

import com.example.weatherappcompose.core.common.Resource
import com.example.weatherappcompose.current_weather.data.repository.remote.WeatherRepositoryImpl
import com.example.weatherappcompose.current_weather.domain.models.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRemoteCountryUseCase @Inject constructor(
    private val weatherRepository: WeatherRepositoryImpl
) {


    operator fun invoke(country: String): Flow<Resource<List<Country>>> = flow {

        try {
            emit(Resource.Loading())
            val countryList = weatherRepository.getCountry(country)
            emit(Resource.Success(countryList))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}







