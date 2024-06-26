package com.example.weatherappcompose.current_weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [CountryEntity::class], version = 1, exportSchema = false)
abstract class CountryDataBase: RoomDatabase() {
    abstract fun countryDao(): CountryDao
}