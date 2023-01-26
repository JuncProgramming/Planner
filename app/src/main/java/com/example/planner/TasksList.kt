package com.example.planner

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planner.ui.theme.PlannerTheme

@Composable
fun TasksList(
    modifier: Modifier = Modifier,
    tasks: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        items(items = tasks) { task ->
            TaskCard(task = task)
        }
    }
}

@Composable
@Preview(name = "LightMode", showBackground = true)
@Preview(name = "LightMode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TasksListPreview() {
    PlannerTheme {
        Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
            TasksList()
        }
    }
}