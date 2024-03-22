package com.example.weatherappcompose.forecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappcompose.core.common.Resource
import com.example.weatherappcompose.forecast.domain.models.Country
import com.example.weatherappcompose.forecast.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _countryState: MutableStateFlow<CountryState> =
        MutableStateFlow(CountryState())
    val countryState = _countryState.asStateFlow()

    private val _forecastState: MutableStateFlow<ForecastState> =
        MutableStateFlow(ForecastState())
    val forecastState = _forecastState.asStateFlow()


    init {
        onEvent(
            ForecastEvent.InitialScreen(
                _countryState.value.country?.latitude,
                _countryState.value.country?.longitude
            )
        )
    }

    fun onEvent(event: ForecastEvent) {
        when (event) {
            is ForecastEvent.GetSevenDayForecast -> {
                getForecastInfo(event.lat, event.lon)
            }

            is ForecastEvent.DeleteAndInsertCountry -> {
                deleteCountry()
                insertCountry(event.country)
            }

            is ForecastEvent.InitialScreen -> {
                viewModelScope.launch {
                    async { getLocalCountry() }.await()
                    if (_countryState.value.country != null) {
                        async { getForecastInfo(event.lat!!, event.lon!!)}.await()
                    }
                }

            }
        }
    }


    fun getCountry(country: String) {
        viewModelScope.launch {
            useCase.getRemoteCountryUseCase(country).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _countryState.update {
                            it.copy(
                                error = result.message!!,
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _countryState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        result.data?.let { country ->
                            _countryState.update {
                                it.copy(
                                    countryState = country,
                                    isLoading = false,
                                    error = ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getForecastInfo(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            useCase.getForecastUseCase(latitude, longitude).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _forecastState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message ?: ""
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _forecastState.update {
                            it.copy(
                                isLoading = true,
                                error = result.message ?: ""
                            )
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { forecastInfo ->
                            _forecastState.update {
                                it.copy(
                                    forecast = forecastInfo,
                                    isLoading = false,
                                    error = ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun insertCountry(country: Country) {
        viewModelScope.launch {
            useCase.insertLocalCountryUC(country)
        }
    }

    private fun deleteCountry() {
        viewModelScope.launch {
            if (_countryState.value.country != null) {
                useCase.deleteLocalCountryUC()
            }
        }
    }

    private fun getLocalCountry() {
        viewModelScope.launch {
            useCase.getLocalCountryUC().collectLatest { result ->
                result.data?.let { country ->
                    when (result) {
                        is Resource.Error -> {
                            _countryState.update {
                                it.copy(
                                    isLoading = false,
                                    error = result.message ?: ""
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _countryState.update {
                                it.copy(
                                    isLoading = true,
                                    error = result.message ?: ""
                                )
                            }
                        }

                        is Resource.Success -> {
                            _countryState.update {
                                it.copy(
                                    country = country,
                                    isLoading = false,
                                    error = ""
                                )
                            }

                        }
                    }
                }

            }
        }
    }


}








