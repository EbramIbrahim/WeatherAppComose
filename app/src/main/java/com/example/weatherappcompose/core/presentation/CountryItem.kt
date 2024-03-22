package com.example.weatherappcompose.core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappcompose.forecast.domain.models.Country
import com.example.weatherappcompose.forecast.presentation.CountryState


@Composable
fun CountryItem(
    countryState: CountryState,
    onClick: (Country) -> Unit
) {

    if (countryState.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    if (countryState.error.isNotEmpty() && !countryState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = countryState.error, color = Color.Red, fontSize = 26.sp)
        }
    }
    if (countryState.countryState.isNotEmpty()) {
        LazyColumn {
            items(countryState.countryState.size) { index ->
                CountryItemInfo(
                    country = countryState.countryState[index],
                    onClick = { onClick(countryState.countryState[index]) })
            }
        }
    }

}


@Composable
fun CountryItemInfo(country: Country, onClick: (Country) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
            .clickable { onClick(country) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(
                text = country.countryName,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = country.town,
                color = Color.White,
                fontSize = 14.sp
            )
        }

        Text(
            text = country.timeZone,
            color = Color.White,
            fontSize = 16.sp
        )

    }
}






