package com.example.weatherappcompose.forecast.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface CountryDao {

    @Upsert
    suspend fun insertCountry(countryEntity: CountryEntity)

    @Query("DELETE FROM country_table")
    suspend fun deleteCountry()


    @Query("SELECT * from country_table")
    fun getCountry(): Flow<CountryEntity>



}