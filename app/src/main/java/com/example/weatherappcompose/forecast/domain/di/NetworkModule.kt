package com.example.weatherappcompose.forecast.domain.di

import android.content.Context
import androidx.room.Room
import com.example.weatherappcompose.core.common.Utils.COUNTRY_BASE_URL
import com.example.weatherappcompose.core.common.Utils.WEATHER_BASE_URL
import com.example.weatherappcompose.forecast.data.local.CountryDataBase
import com.example.weatherappcompose.forecast.data.remote.CountryApi
import com.example.weatherappcompose.forecast.data.remote.WeatherApi
import com.example.weatherappcompose.forecast.domain.usecase.DeleteLocalCountryUC
import com.example.weatherappcompose.forecast.domain.usecase.GetRemoteCountryUseCase
import com.example.weatherappcompose.forecast.domain.usecase.GetForecastUseCase
import com.example.weatherappcompose.forecast.domain.usecase.GetLocalCountryUC
import com.example.weatherappcompose.forecast.domain.usecase.InsertLocalCountryUC
import com.example.weatherappcompose.forecast.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Provides
    @Singleton
    fun provideInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApiInstance(okHttpClient: OkHttpClient): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryApiInstance(okHttpClient: OkHttpClient): CountryApi {
        return Retrofit.Builder()
            .baseUrl(COUNTRY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryDataBase(
        @ApplicationContext context: Context
    ): CountryDataBase {
        return Room.databaseBuilder(
            context,
            CountryDataBase::class.java,
            "movie"
        ).build()
    }


    @Provides
    @Singleton
    fun provideUseCase(
        getRemoteCountryUseCase: GetRemoteCountryUseCase,
        getForecastUseCase: GetForecastUseCase,
        insertLocalCountryUC: InsertLocalCountryUC,
        deleteLocalCountryUC: DeleteLocalCountryUC,
        getLocalCountryUC: GetLocalCountryUC
    ): UseCase {
        return UseCase(
            getRemoteCountryUseCase,
            getForecastUseCase,
            insertLocalCountryUC = insertLocalCountryUC,
            deleteLocalCountryUC = deleteLocalCountryUC,
            getLocalCountryUC = getLocalCountryUC

        )
    }

}