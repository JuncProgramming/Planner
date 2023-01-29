package com.example.planner

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.planner.ui.theme.PlannerTheme

@Composable
fun TasksList(
    modifier: Modifier = Modifier,
    tasks: List<String> = List(1000) { "Preview task" },
    navController: NavController
) {
    LazyColumn(modifier = modifier.padding(horizontal = 12.dp, vertical = 12.dp), verticalArrangement = spacedBy(12.dp)) {
        items(items = tasks) { task ->
            TaskCard(task = task, navController = navController)
        }
    }
}

@Composable
@Preview(name = "LightMode", showBackground = true)
@Preview(name = "LightMode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TasksListPreview() {
    PlannerTheme {
        Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
            TasksList(navController = rememberNavController())
        }
    }
}