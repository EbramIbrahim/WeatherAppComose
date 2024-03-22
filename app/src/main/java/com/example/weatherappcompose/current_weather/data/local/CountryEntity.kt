package com.example.weatherappcompose.current_weather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
data class CountryEntity(
    val countryName: String,
    @PrimaryKey(autoGenerate = false)
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val town: String
)
