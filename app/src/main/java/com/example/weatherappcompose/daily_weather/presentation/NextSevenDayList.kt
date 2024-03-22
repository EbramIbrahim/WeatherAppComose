package com.example.weatherappcompose.daily_weather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherappcompose.core.presentation.ForecastState

@Composable
fun NextSevenDayListScreen(forecastState: ForecastState) {

    forecastState.forecast?.weatherDataPerDay?.let {
        Column(modifier = Modifier.fillMaxSize()) {
            repeat(it.size) { index ->
                it[index]?.get(0)?.let { forecast ->
                    NextSevenDayScreen(forecast = forecast)
                }
            }
        }
    }

}