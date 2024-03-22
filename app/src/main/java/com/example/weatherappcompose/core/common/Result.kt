package com.example.weatherappcompose.core.common

// Using this class if I want to Handle multiple Exceptions

sealed interface Result<out DATA, out ERROR: Exception> {
    data class Success<out DATA, out ERROR: Exception>(val data: DATA): Result<DATA, ERROR>

    data class Failure<out ERROR: Exception>(val error: ERROR): Result<Nothing, ERROR>

}