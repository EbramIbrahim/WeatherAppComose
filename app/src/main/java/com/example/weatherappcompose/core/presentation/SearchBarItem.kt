package com.example.weatherappcompose.core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.forecast.presentation.CountryState
import com.example.weatherappcompose.forecast.presentation.ForecastEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarItem(
    countryState: CountryState,
    onSearch: (String) -> Unit,
    onEvent: (ForecastEvent) -> Unit
) {

    var searchQuery by remember {
        mutableStateOf("")
    }

    var isActive by remember {
        mutableStateOf(false)
    }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        query = searchQuery,
        onQueryChange = { searchQuery = it },
        onSearch = { onSearch(searchQuery) },
        active = isActive,
        onActiveChange = {
            isActive = it
        },
        placeholder = {
            Text(text = "Search Country's Weather")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        }, trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,

                modifier = Modifier.clickable {
                    if (searchQuery.isNotEmpty()) {
                        searchQuery = ""
                    } else {
                        isActive = false
                    }
                }
            )
        }
    ) {
        CountryItem(
            countryState = countryState,
            onClick = {
                onEvent(ForecastEvent.GetSevenDayForecast(it.latitude, it.longitude))
                onEvent(ForecastEvent.DeleteAndInsertCountry(it))
                isActive = false
            }
        )
    }

}



