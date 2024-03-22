package com.example.weatherappcompose.current_weather.domain.di


import com.example.weatherappcompose.current_weather.data.repository.remote.WeatherRepositoryImpl
import com.example.weatherappcompose.current_weather.domain.repository.remote.IWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun provideRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): IWeatherRepository


}