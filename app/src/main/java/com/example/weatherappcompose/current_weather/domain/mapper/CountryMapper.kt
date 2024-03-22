package com.example.weatherappcompose.current_weather.domain.mapper

import com.example.weatherappcompose.core.domain.mapper.Mapper
import com.example.weatherappcompose.current_weather.data.dto.Result
import com.example.weatherappcompose.current_weather.data.local.CountryEntity
import com.example.weatherappcompose.current_weather.domain.models.Country

object CountryMapper: Mapper<Country, CountryEntity, Result> {

    override fun dtoToDomain(dto: Result): Country {
        return Country(
            countryName = dto.name,
            latitude = dto.latitude,
            longitude = dto.longitude,
            timeZone = dto.timezone,
            town = dto.admin1
        )
    }

    override fun entityToDomain(entity: CountryEntity): Country {
        return Country(
            countryName = entity.countryName,
            latitude = entity.lat,
            longitude = entity.lon,
            timeZone = entity.timezone,
            town = entity.town
        )
    }


    override fun domainToEntity(domain: Country): CountryEntity {
        return CountryEntity(
            countryName = domain.countryName,
            lat = domain.latitude,
            lon = domain.longitude,
            timezone = domain.timeZone,
            town = domain.town
        )
    }
}