package com.example.weatherappcompose.current_weather.domain.usecase

data class UseCase(
    val getRemoteCountryUseCase: GetRemoteCountryUseCase,
    val getForecastUseCase: GetForecastUseCase,
    val insertLocalCountryUC: InsertLocalCountryUC,
    val getLocalCountryUC: GetLocalCountryUC,
    val deleteLocalCountryUC: DeleteLocalCountryUC
)
