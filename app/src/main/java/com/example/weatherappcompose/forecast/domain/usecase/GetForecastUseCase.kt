package com.example.weatherappcompose.forecast.domain.usecase

import com.example.weatherappcompose.core.common.Resource
import com.example.weatherappcompose.forecast.data.repository.remote.WeatherRepositoryImpl
import com.example.weatherappcompose.forecast.domain.models.ForecastInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepositoryImpl
) {

    operator fun invoke(latitude: Double, longitude: Double):
            Flow<Resource<ForecastInfo>> = flow {
        try {
            emit(Resource.Loading())

            val forecastInfo = weatherRepository.getForecast(latitude, longitude)
            emit(Resource.Success(forecastInfo))

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Message"))
        }
    }
}






