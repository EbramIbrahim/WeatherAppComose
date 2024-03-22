package com.example.weatherappcompose.core.domain.mapper

interface ForecastMapper<DOMAIN, DTO, INFO> {

    fun dtoToDomain(dto: DTO): Map<Int, List<DOMAIN>>

    fun dtoToInfo(dto: DTO): INFO

}