package com.junclabs.planner.ui.task_list

import android.Manifest
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.junclabs.planner.R
import com.junclabs.planner.data.Task
import com.junclabs.planner.ui.theme.RoundedShapes

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    Card(
        shape = RoundedShapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = modifier.clickable {
                permissionState.launchPermissionRequest()
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
                Icon(
                    painter = painterResource(id = R.drawable.baseline_alarm_24),
                    contentDescription = stringResource(R.string.editButton_cd),
                    modifier = modifier.padding(horizontal = 8.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
