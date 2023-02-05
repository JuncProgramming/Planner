package com.example.planner.ui.task_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.planner.data.Task

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onEvent: (TaskListEvent) -> Unit,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ), modifier = modifier.padding()
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(12.dp)

            ) {
                Column(
                    modifier = modifier
                        .weight(1f)
                        .padding(12.dp)
                ) {
                    task.categoryName?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = task.taskName,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                IconButton(
                    onClick = { viewModel.onEvent(TaskListEvent.OnEditTask(task)) },
                    modifier = modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Edit",
                        modifier = modifier.padding(horizontal = 2.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                IconButton(
                    onClick = { }, modifier = modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = "Notifications",
                        modifier = modifier.padding(horizontal = 2.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

/*
@Composable
@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TaskCardPreview() {
    PlannerTheme {
        Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
            TaskCard(
                task = Task(taskN = "xd", categoryN = "xdr"))
            )
        }
    }
}
*/