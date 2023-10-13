package com.junclabs.planner.ui.task_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junclabs.planner.data.Task
import com.junclabs.planner.ui.theme.RoundedShapes

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    Card(
        shape = RoundedShapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = modifier.clickable {
                viewModel.onEvent(TaskListEvent.OnEditClick(task))
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(12.dp)
            ) {
                Column(
                    modifier = modifier
                        .weight(1f)
                        .padding(12.dp)
                ) {
                    if (!task.categoryName.isNullOrEmpty()) {
                        Text(
                            text = task.categoryName,
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
            }
        }
    }
}
