package com.example.weatherappcompose.current_weather.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherappcompose.core.presentation.CountryState
import com.example.weatherappcompose.core.presentation.ForecastState
import com.example.weatherappcompose.core.presentation.SearchBarItem
import com.example.weatherappcompose.core.presentation.ThemeSwitcher
import com.example.weatherappcompose.current_weather.presentation.current_weather_screen.WeatherCard
import com.example.weatherappcompose.current_weather.presentation.weather_ber_day.HourlyForecastScreen
import com.example.weatherappcompose.daily_weather.presentation.ForecastEvent

@Composable
fun MainScreen(
    country: CountryState,
    forecast: ForecastState,
    onEvent: (ForecastEvent) -> Unit,
    getCountry: (String) -> Unit,
    isDarkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    navController: NavController
) {


    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {

        if (forecast.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        if (forecast.error.isNotEmpty() && !forecast.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "This City isn't found", color = Color.Red, fontSize = 16.sp)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            ThemeSwitcher(darkTheme = isDarkTheme, onThemeUpdated = onThemeUpdated)

        }

        SearchBarItem(
            countryState = country,
            onSearch = { getCountry(it) },
            onEvent = { onEvent(it) }
        )

        if (forecast.forecast != null) {
            Spacer(modifier = Modifier.height(8.dp))

            WeatherCard(state = forecast)

            Spacer(modifier = Modifier.height(8.dp))

            HourlyForecastScreen(forecast, navController)
        }

    }
}