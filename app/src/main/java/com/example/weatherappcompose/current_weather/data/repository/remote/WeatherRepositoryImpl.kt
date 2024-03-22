package com.example.weatherappcompose.current_weather.data.repository.remote

import com.example.weatherappcompose.current_weather.data.remote.CountryApi
import com.example.weatherappcompose.current_weather.data.remote.WeatherApi
import com.example.weatherappcompose.current_weather.domain.mapper.CountryMapper
import com.example.weatherappcompose.current_weather.domain.mapper.ForecastMapperImpl
import com.example.weatherappcompose.current_weather.domain.models.Country
import com.example.weatherappcompose.current_weather.domain.models.ForecastInfo
import com.example.weatherappcompose.current_weather.domain.repository.remote.IWeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val countryApi: CountryApi
): IWeatherRepository {

    override suspend fun getCountry(query: String): List<Country> {
        val remoteCountryData = countryApi.getCountry(query)
        val country = remoteCountryData.results.map {
            CountryMapper.dtoToDomain(it)
        }
        return country
    }


    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): ForecastInfo {
        val forecast = weatherApi.getWeatherData(latitude, longitude)
       return ForecastMapperImpl.dtoToInfo(forecast)
    }
}









