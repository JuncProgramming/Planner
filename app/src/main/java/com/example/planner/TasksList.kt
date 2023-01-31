package com.example.planner

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.planner.ui.theme.PlannerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksListLayout(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = Screen.AddScreen.route) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add a task")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Planner") }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.background
                )
            )
        },
    ) { padding ->
        TasksList(
            Modifier.padding(padding), List(1000) { "Preview task" }, navController
        )
    }
}

@Composable
fun TasksList(
    modifier: Modifier = Modifier, tasks: List<String>, navController: NavController
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 12.dp),
        verticalArrangement = spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(items = tasks) { task ->
            TaskCard(task = task, navController = navController)
        }
    }
}

@Composable
@Preview(name = "LightMode", showBackground = true)
@Preview(name = "LightMode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TasksListLayoutPreview() {
    PlannerTheme {
        Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
            TasksListLayout(navController = rememberNavController())
        }
    }
}