package com.example.weatherappcompose.forecast.presentation.weather_ber_day

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappcompose.forecast.presentation.ForecastState

@Composable
fun ForecastDetailsScreen(
    forecastState: ForecastState
) {

    forecastState.forecast?.weatherDataPerDay?.get(0)?.let { forecastPerDay ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Today", fontSize = 16.sp, fontWeight = FontWeight.Bold)

            Text(
                text = "next 7Days",
                fontSize = 16.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { }
            )

        }

        Spacer(modifier = Modifier.height(8.dp))


        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(forecastPerDay.size) { index ->
                HourlyForecastScreen(forecast = forecastPerDay[index])
            }
        }

    }


//    forecastState.forecast?.weatherDataPerDay?.let {
//        LazyColumn {
//            items(it.size) { index ->
//                it[index]?.get(0)?.let { forecast ->
//                    NextSevenDayScreen(forecast = forecast)
//                }
//            }
//        }
//    }


}










