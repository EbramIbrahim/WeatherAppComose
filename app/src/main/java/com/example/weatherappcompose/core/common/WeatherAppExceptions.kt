package com.example.weatherappcompose.core.common

sealed class WeatherAppExceptions(message: String): Exception(message) {

    data class RequestTimeOut(val requestTimeOutMessage: String) :
        WeatherAppExceptions(requestTimeOutMessage)
    data class TooManyRequest(val tooManyRequestMessage: String) :
        WeatherAppExceptions(tooManyRequestMessage)
    data class NoInternet(val noInternetMessage: String) :
        WeatherAppExceptions(noInternetMessage)
    data class ServerError(val serverErrorMessage: String) :
        WeatherAppExceptions(serverErrorMessage)

}