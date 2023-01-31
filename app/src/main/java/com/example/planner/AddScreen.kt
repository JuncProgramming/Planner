package com.example.planner

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planner.ui.theme.PlannerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenLayout() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Planner") }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.background,
                navigationIconContentColor = MaterialTheme.colorScheme.background
            ), navigationIcon = {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Navigate back",
                    modifier = Modifier.padding(16.dp)
                )
            })
        },
    ) { padding ->
        AddScreen(
            Modifier.padding(paddingValues = padding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(modifier: Modifier = Modifier) {

    var isTaskClicked by remember { mutableStateOf(false) }
    var isCategoryClicked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp, vertical = 4.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        var task by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }

        OutlinedTextField(colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.outline),
            value = task,
            onValueChange = { task = it },
            label = { Text("Task") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .onFocusChanged {
                    isTaskClicked = !isTaskClicked
                })

        OutlinedTextField(colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.outline),
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .onFocusChanged {
                    isCategoryClicked = !isCategoryClicked
                }

        )

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text(text = "Add")
        }
    }
}

@Composable
@Preview(name = "LightMode", showBackground = true)
@Preview(name = "DarkMode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun AddScreenLayoutPreview() {
    PlannerTheme {
        Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
            AddScreenLayout()
        }
    }
}