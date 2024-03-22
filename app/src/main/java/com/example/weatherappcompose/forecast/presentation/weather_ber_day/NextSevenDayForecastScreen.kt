package com.example.weatherappcompose.forecast.presentation.weather_ber_day

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherappcompose.forecast.domain.models.Forecast

@Composable
fun NextSevenDayScreen(
    forecast: Forecast
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
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

        Icon(
            painter = painterResource(id = forecast.weatherType.iconRes),
            modifier = Modifier.size(64.dp),
            contentDescription = ""
        )


    }


}