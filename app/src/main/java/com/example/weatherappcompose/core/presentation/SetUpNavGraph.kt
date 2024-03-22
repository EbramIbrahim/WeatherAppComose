package com.example.weatherappcompose.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherappcompose.daily_weather.presentation.ForecastEvent
import com.example.weatherappcompose.current_weather.presentation.MainScreen
import com.example.weatherappcompose.daily_weather.presentation.NextSevenDayListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    country: CountryState,
    forecast: ForecastState,
    onEvent: (ForecastEvent) -> Unit,
    getCountry: (String) -> Unit,
    isDarkTheme: Boolean,
    onThemeUpdated: () -> Unit
    ) {

    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {
        
        composable(route = Screens.MainScreen.route) {
            MainScreen(
                country = country,
                forecast = forecast,
                onEvent = { onEvent(it) },
                getCountry = {getCountry(it)},
                isDarkTheme = isDarkTheme,
                onThemeUpdated = onThemeUpdated,
                navController = navController
            )
        }


        composable(route = Screens.SevenDaysForecastScreen.route) {
            NextSevenDayListScreen(forecastState = forecast)
        }
        
    }
    
}












