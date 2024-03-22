package com.example.weatherappcompose.current_weather.presentation.weather_ber_day

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.current_weather.domain.models.Forecast
import java.time.format.DateTimeFormatter

@Composable
fun HourlyForecastCard(
    forecast: Forecast
) {

    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
        ,
        elevation = CardDefaults.cardElevation(2.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Image(
                painter = painterResource(id = forecast.weatherType.iconRes),
                modifier = Modifier.size(64.dp).padding(bottom = 4.dp),
                contentScale = ContentScale.Fit,
                contentDescription = ""
            )
            Text(
                text = forecast.time.format(
                    DateTimeFormatter.ofPattern("HH"),
                ),
                modifier = Modifier.padding(4.dp),

            )
            Text(
                text = "${forecast.temperatureCelsius}°C",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}











