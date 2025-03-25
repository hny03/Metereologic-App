package com.example.metereologic_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.metereologic_app.ui.feature.WeatherRoute
import com.example.metereologic_app.ui.theme.MetereologicAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MetereologicAppTheme {
                WeatherRoute()
            }
        }
    }
}
