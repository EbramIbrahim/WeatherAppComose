package com.example.weatherappcompose.forecast.domain.di

import android.content.Context
import androidx.room.Room
import com.example.weatherappcompose.forecast.data.local.CountryDataBase
import com.example.weatherappcompose.forecast.data.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, CountryDataBase::class.java)
            .allowMainThreadQueries()
            .build()


    @Provides
    fun provideTestApi(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .build()
            .create(WeatherApi::class.java)

}