package com.example.metereologic_app.ui.mainscreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metereologic_app.R
import com.example.metereologic_app.data.model.WeatherForecast
import com.example.metereologic_app.data.model.WeatherInfo
import com.example.metereologic_app.ui.feature.WeatherScreen
import com.example.metereologic_app.ui.feature.WeatherViewModel
import com.example.metereologic_app.ui.theme.BlueSky
import com.example.metereologic_app.ui.theme.DarkBlueSky
import com.example.metereologic_app.ui.theme.DarkNightBlue
import com.example.metereologic_app.ui.theme.LightBlueSky
import com.example.metereologic_app.ui.theme.LightNightBlue
import com.example.metereologic_app.ui.theme.NightBlue
import java.time.Instant.ofEpochSecond
import java.time.format.DateTimeFormatter.ofPattern


@Composable
fun MainRoute(
    viewModel: WeatherViewModel = viewModel()
) {
    val weatherInfoState by viewModel.weatherInfoState.collectAsStateWithLifecycle()

    MainScreen(weatherInfo = weatherInfoState.weatherInfo, viewModel = viewModel)
}



@Composable
fun MainScreen(
    context: Context = LocalContext.current,
    weatherInfo: WeatherInfo?,
    viewModel: WeatherViewModel
) {
    weatherInfo?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = if (weatherInfo.isDay) {
                            listOf(BlueSky, LightBlueSky)
                        } else listOf(NightBlue, LightNightBlue)
                    )
                )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            label = { Text("Digite a cidade") },
                            placeholder = { Text("Ex: São Paulo, Nova York...") },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = "Buscar"
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Search
                            ),
                            shape = RoundedCornerShape(24.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                disabledContainerColor = Color.White
                            )
                        )
                    }
                    item {
                        Text(
                            text = "${weatherInfo.locationName}",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 48.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "${weatherInfo.condition}",
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 48.dp),
                            textAlign = TextAlign.Center
                        )

                        val iconDrawableResId: Int = context.resources.getIdentifier(
                            "weather_${weatherInfo.conditionIcon}",
                            "drawable",
                            context.packageName
                        )



                        Image(
                            painter = painterResource(id = iconDrawableResId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(150.dp)
                                .padding(top = 8.dp)
                        )

                        Text(
                            text = "${weatherInfo.dayOfWeek}",
                            fontSize = 19.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )

                        // Display temperature details
                        Text(
                            text = "${weatherInfo.temperature}°C",
                            fontSize = 69.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "Sensação Termica: ${weatherInfo.feelsLike}°C",
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )

                        val sunriseTimeFormatted = ofEpochSecond(weatherInfo.sunriseTime)
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalTime()
                            .minusHours(3)
                            .format(ofPattern("HH:mm"))

                        val sunsetTimeFormatted = ofEpochSecond(weatherInfo.sunsetTime)
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalTime()
                            .minusHours(3)
                            .format(ofPattern("HH:mm"))

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                                .background(
                                    color = if (weatherInfo.isDay) {
                                        DarkBlueSky
                                    } else DarkNightBlue,
                                    shape = RoundedCornerShape(25.dp)
                                ),
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                WeatherDetailItem(
                                    icon = R.drawable.sunrise,
                                    value = "$sunriseTimeFormatted",
                                    label = null
                                )
                                WeatherDetailItem(
                                    icon = R.drawable.sunset,
                                    value = "$sunsetTimeFormatted",
                                    label = null
                                )

                            }
                        }

                        // Box containing weather detail like rain, wind spped, etc

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                                .background(
                                    color = if (weatherInfo.isDay) {
                                        DarkBlueSky
                                    } else DarkNightBlue,
                                    shape = RoundedCornerShape(25.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                WeatherDetailItem(
                                    icon = R.drawable.tmin,
                                    value = "${weatherInfo.tempMin}",
                                    label = "Min"
                                )

                                WeatherDetailItem(
                                    icon = R.drawable.tmax,
                                    value = "${weatherInfo.tempMax}",
                                    label = "Max"
                                )

                                WeatherDetailItem(
                                    icon = R.drawable.wind,
                                    value = "${weatherInfo.windSpeed}",
                                    label = "Vento"
                                )

                                WeatherDetailItem(
                                    icon = R.drawable.humidity,
                                    value = "${weatherInfo.humidity}%",
                                    label = "Humidade"
                                )
                            }
                        }

                        // Displaying "Today" label
                        Text(
                            text = "Today",
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 24.dp,
                                    vertical = 8.dp
                                )
                        )

                    }

                    // Display Future hourly forecast using a LazyRow
                    item {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(weatherInfo.forecasts) { item ->
                                FutureModelViewHolder(item, weatherInfo, context)
                            }
                        }
                    }

                    // Display "Future Label and next 7 day button"
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Cidades Pesquisadas",
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    items(dailyItems) {
                        FutureItem(item = it)
                    }
                }
            }

        }
    }
}

// Função para pegar as cidades
fun CidadeItem() {

}


// Display each future daily forecast item
@Composable
fun FutureItem(item: FutureModel) {
    Row(
        modifier =  Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.day,
            color = Color.White,
            fontSize = 14.sp
        )

        Image(painter = painterResource(
            id = getDrawableResourceId(picPath = item.picPath)),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 32.dp)
                .size(45.dp)
        )
        Text(
            text = item.status,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            color = Color.White,
            fontSize = 14.sp
        )
        Text(
            text = "${item.highTemp}",
            modifier = Modifier.padding(end = 16.dp),
            color = Color.White,
            fontSize = 14.sp
        )
        Text(
            text = "${item.lowTemp}",
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
fun getDrawableResourceId(picPath: String): Int {
    return when(picPath){
        "storm"->R.drawable.storm
        "cloudy"->R.drawable.cloudy
        "windy"->R.drawable.windy
        "cloudy_sunny"->R.drawable.cloudy_sunny
        "sunny"->R.drawable.sunny
        "rainy"->R.drawable.rainy
        else->R.drawable.sunny
    }
}

// Sample daily data
val dailyItems= listOf(
    FutureModel("Sat", "storm", "Storm", 24, 12),
    FutureModel("Sun", "cloudy", "Cloudy", 25, 16),
    FutureModel("Mon", "windy", "Windy", 29, 15),
    FutureModel("Tue", "cloudy_sunny", "Cloudy Sunny", 23, 15),
    FutureModel("Wen", "sunny", "Sunny", 28, 11),
    FutureModel("Thu", "rainy", "Rainy", 23, 12)
)

// Viewholder for each hourly forecast item
@Composable
fun FutureModelViewHolder(forecast: WeatherForecast, weatherInfo: WeatherInfo?, context: Context) {
    if (weatherInfo != null) {
        Column(
            modifier = Modifier
                .width(90.dp)
                .wrapContentHeight()
                .padding(4.dp)
                .background(
                    color = if (weatherInfo.isDay) {
                        DarkBlueSky
                    } else DarkNightBlue,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val timeOnly = forecast.dateTimeText.substring(11, 16)
            Text(
                text = "$timeOnly",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )

            val icon: Int = context.resources.getIdentifier(
                "weather_${forecast.conditionIcon}",
                "drawable",
                context.packageName
            )

            Image(
                painter = painterResource(
                    id = icon
                ), contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "${forecast.temperature}°C",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun WeatherDetailItem(icon: Int, value: String, label: String?) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(34.dp)
        )

        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        label?.let {
            Text(
                text = it,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}