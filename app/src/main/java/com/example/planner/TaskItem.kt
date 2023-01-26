package com.example.planner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planner.ui.theme.PlannerTheme

@Composable
fun TaskCard(task: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ), modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        CardContent(task)
    }
}

@Composable
fun CardContent(task: String) {

    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)

        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                    text = "Category",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = task,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit",
                modifier = Modifier.padding(horizontal = 4.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notifications",
                modifier = Modifier.padding(horizontal = 4.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
@Preview
fun TaskCardPreview() {
    PlannerTheme() {
        TaskCard(task = "Preview")
    }
}