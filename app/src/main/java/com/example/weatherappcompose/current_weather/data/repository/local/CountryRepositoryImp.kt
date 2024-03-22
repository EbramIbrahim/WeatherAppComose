package com.example.weatherappcompose.current_weather.data.repository.local

import com.example.weatherappcompose.current_weather.data.local.CountryDataBase
import com.example.weatherappcompose.current_weather.data.local.CountryEntity
import com.example.weatherappcompose.current_weather.domain.mapper.CountryMapper
import com.example.weatherappcompose.current_weather.domain.models.Country
import com.example.weatherappcompose.current_weather.domain.repository.local.ICountryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryRepositoryImp @Inject constructor(
    private val dao: CountryDataBase
): ICountryRepository {

    override suspend fun insertCountry(country: Country) {
        dao.countryDao().insertCountry(CountryMapper.domainToEntity(country))
    }

    override suspend fun deleteCountry() {
        dao.countryDao().deleteCountry()
    }

    override fun getCountry(): Flow<CountryEntity> {
        return dao.countryDao().getCountry()
    }
}