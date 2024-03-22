package com.example.weatherappcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherappcompose.core.presentation.SearchBarItem
import com.example.weatherappcompose.core.presentation.ThemeSwitcher
import com.example.weatherappcompose.forecast.presentation.WeatherViewModel
import com.example.weatherappcompose.forecast.presentation.current_weather_screen.WeatherCard
import com.example.weatherappcompose.forecast.presentation.weather_ber_day.ForecastDetailsScreen
import com.example.weatherappcompose.ui.theme.WeatherAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(true) }
            WeatherAppComposeTheme(darkTheme = isDarkTheme) {
                val viewModel = hiltViewModel<WeatherViewModel>()
                val country by viewModel.countryState.collectAsStateWithLifecycle()
                val forecast by viewModel.forecastState.collectAsStateWithLifecycle()



                Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        ThemeSwitcher(darkTheme = isDarkTheme, onThemeUpdated = { isDarkTheme = !isDarkTheme })

                    }

                    SearchBarItem(
                        countryState = country,
                        onSearch = viewModel::getCountry,
                        onEvent = viewModel::onEvent
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    WeatherCard(state = forecast)

                    Spacer(modifier = Modifier.height(8.dp))

                    ForecastDetailsScreen(forecast)
                }

                Log.e("country", country.country.toString())


            }
        }
    }
}

