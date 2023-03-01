package com.junclabs.planner.ui.task_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junclabs.planner.R
import com.junclabs.planner.data.Task
import com.junclabs.planner.ui.theme.RoundedShapes

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
        )
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant
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
                IconButton(
                    onClick = { viewModel.onEvent(TaskListEvent.OnEditClick(task)) },
                    modifier = modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = stringResource(R.string.editButton_cd),
                        modifier = modifier.padding(horizontal = 2.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}