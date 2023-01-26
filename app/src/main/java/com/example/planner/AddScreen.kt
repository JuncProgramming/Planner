package com.example.planner

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun AddScreen() {

    var isTaskClicked by remember { mutableStateOf(false) }
    var isCategoryClicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp, end = 16.dp, top = if (isTaskClicked) {
                    0.dp
                } else 4.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        var task by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }

        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.outline),
            value = task,
            onValueChange = { task = it },
            label = { Text("Task") },
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessHigh))
                .padding(vertical = 8.dp)
                .onFocusChanged {
                    isTaskClicked = !isTaskClicked
                }
        )

        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.outline),
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = if (isCategoryClicked) {
                        0.dp
                    } else 4.dp
                )
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
fun AddScreenPreview() {
    PlannerTheme {
        Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
            AddScreen()
        }
    }
}