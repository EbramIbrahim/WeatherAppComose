package com.example.weatherappcompose.current_weather.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CountryDaoTest {



    private lateinit var countryDao: CountryDao
    private lateinit var countryDataBase: CountryDataBase


    @Before
    fun setup() {
        countryDataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CountryDataBase::class.java
        ).allowMainThreadQueries().build()
        countryDao = countryDataBase.countryDao()
    }

    @After
    fun teardown() {
        countryDataBase.close()
    }

    @Test
    fun insertCountryItem() = runTest {
        val countryItem = CountryEntity(
            "name", 53.3, 45.2, "", ""
        )
        countryDao.insertCountry(countryItem)

        val allCountryItems = countryDao.getCountry().first()

        assertThat(allCountryItems).isEqualTo(countryItem)
    }

    @Test
    fun deleteShoppingItem() = runTest {
        val countryItem = CountryEntity(
            "name", 53.3, 45.2, "", ""
        )
        countryDao.insertCountry(countryItem)
        countryDao.deleteCountry()

        val allShoppingItems = countryDao.getCountry().first()

        assertThat(allShoppingItems).isNotEqualTo(countryItem)
    }


}






