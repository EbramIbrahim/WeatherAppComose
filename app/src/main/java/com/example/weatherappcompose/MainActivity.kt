package com.example.weatherappcompose

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.weatherappcompose.core.presentation.NavGraph
import com.example.weatherappcompose.current_weather.presentation.WeatherViewModel
import com.example.weatherappcompose.ui.theme.WeatherAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(true) }
            val context = LocalContext.current
            val itemKey = "item${UUID.randomUUID()}"
            val sharedPreference: SharedPreferences = remember {
                context.getSharedPreferences("_MyPref", Context.MODE_PRIVATE)
            }


            WeatherAppComposeTheme(darkTheme = isDarkTheme) {
                val viewModel = hiltViewModel<WeatherViewModel>()
                val country by viewModel.countryState.collectAsStateWithLifecycle()
                val forecast by viewModel.forecastState.collectAsStateWithLifecycle()
                val navController = rememberNavController()

                NavGraph(
                    country = country,
                    forecast = forecast,
                    onEvent = viewModel::onEvent,
                    getCountry = viewModel::getCountryFromRemote,
                    isDarkTheme = isDarkTheme,
                    onThemeUpdated = {
                      isDarkTheme = !isDarkTheme
                    },
                    navController = navController
                )

                Log.e("country", country.country.toString())


            }
        }
    }
}

