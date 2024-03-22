package com.example.weatherappcompose.daily_weather.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.current_weather.domain.models.Forecast

@Composable
fun NextSevenDayScreen(
    forecast: Forecast
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = forecast.time.dayOfWeek.toString(),
            fontWeight = FontWeight.Bold
        )


        Text(
            text = "${forecast.temperatureCelsius}°C",
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Image(
            painter = painterResource(id = forecast.weatherType.iconRes),
            modifier = Modifier.size(64.dp),
            contentDescription = "",
            contentScale = ContentScale.Fit
        )


    }


}