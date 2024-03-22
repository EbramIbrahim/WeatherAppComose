package com.example.weatherappcompose.core.domain.mapper

interface Mapper<DOMAIN, ENTITY, DTO> {

    fun dtoToDomain(dto: DTO): DOMAIN

    fun entityToDomain(entity: ENTITY): DOMAIN

    fun domainToEntity(domain: DOMAIN): ENTITY


}