package com.example.weatherappcompose.forecast.domain.usecase

data class UseCase(
    val getRemoteCountryUseCase: GetRemoteCountryUseCase,
    val getForecastUseCase: GetForecastUseCase,
    val insertLocalCountryUC: InsertLocalCountryUC,
    val getLocalCountryUC: GetLocalCountryUC,
    val deleteLocalCountryUC: DeleteLocalCountryUC
)
