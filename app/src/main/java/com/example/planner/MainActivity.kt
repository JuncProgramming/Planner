package com.example.planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planner.ui.theme.PlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlannerTheme {
                // A surface container using the 'background' color from the theme
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

@Composable
fun TaskCard(task: String) {
    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary
    ), modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        CardContent(task)
    }
}

@Composable
fun CardContent(task: String) {

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
        ) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(12.dp)
            ) {
                Text(text = "Hello, ")
                Text(text = task, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold))
            }
            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
            Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notifications")
        }
    }
}

@Composable
private fun TasksList(
    modifier: Modifier = Modifier,
    tasks: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = tasks) {task ->
            TaskCard(task = task)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlannerTheme {
        TaskCard("Android")
    }
}