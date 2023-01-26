package com.example.planner

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.planner.ui.theme.PlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlannerTheme {
                // A surface container using the "background" color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        TasksList()
    }
}

@Preview(name = "LightMode", showBackground = true)
@Preview(name = "DarkMode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "FullPreview", showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    PlannerTheme {
        Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
            TasksList()
        }
    }
}
