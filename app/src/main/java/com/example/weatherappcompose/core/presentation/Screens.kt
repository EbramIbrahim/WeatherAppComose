package com.example.weatherappcompose.core.presentation

sealed class Screens(val route: String) {

    data object MainScreen: Screens("main_screen")
    data object SevenDaysForecastScreen: Screens("seve_days_screen")

}