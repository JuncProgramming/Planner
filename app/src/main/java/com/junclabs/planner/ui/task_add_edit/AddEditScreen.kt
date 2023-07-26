package com.junclabs.planner.ui.task_add_edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junclabs.AlarmItem
import com.junclabs.planner.AndroidAlarmScheduler
import com.junclabs.planner.R
import com.junclabs.planner.ui.SnackBarController
import com.junclabs.planner.ui.theme.RoundedShapes
import com.junclabs.planner.util.UiEvent
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    onPopBackStack: () -> Unit, viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val scheduler: AndroidAlarmScheduler
    var alarmItem: AlarmItem? = null
    val coroutineScope = rememberCoroutineScope()
    val snackBarController = SnackBarController(coroutineScope)
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.PopBackStack -> {
                    snackBarController.cancelActiveJob()
                    onPopBackStack()
                }

                is UiEvent.ShowSnackbar -> {
                    snackBarController.showSnackBar(
                        message = event.message,
                        actionLabel = event.action,
                        duration = SnackbarDuration.Short,
                        snackBarHostState = snackbarHostState
                    )
                }

                else -> Unit
            }
        }
    }
    Scaffold(
        snackbarHost = {
            // Reuse default SnackbarHost to have default animation and timing handling
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    shape = RoundedShapes.medium,
                    actionColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background,
                    snackbarData = data,
                    modifier = Modifier.padding(WindowInsets.ime.asPaddingValues()),
                )
            }
        },
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.planner_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.background
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { onPopBackStack() },
                        modifier = Modifier.padding(horizontal = 16.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.navigationButton_cd)
                        )
                    }
                })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 16.dp, vertical = 4.dp
                )
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = viewModel.taskName,
                onValueChange = { viewModel.onEvent(AddEditTaskEvent.OnTaskNameChange(it)) },
                label = { Text(stringResource(R.string.task_textfield)) },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = viewModel.categoryName,
                onValueChange = { viewModel.onEvent(AddEditTaskEvent.OnCategoryNameChange(it)) },
                label = { Text(stringResource(R.string.category_textfield)) },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    viewModel.onEvent(AddEditTaskEvent.OnSaveTodoClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = stringResource(R.string.button_cd),
                    tint = MaterialTheme.colorScheme.background
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            TimePicker(viewModel)
            Spacer(modifier = Modifier.height(24.dp))
            Divider(modifier = Modifier.height(3.dp))
            Spacer(modifier = Modifier.height(48.dp))
            DatePicker(viewModel)
            Row() {
                Button(
                    onClick = {
                        viewModel.onEvent(AddEditTaskEvent.OnSaveAlarmClick)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 3.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_alarm_on_24),
                        contentDescription = stringResource(R.string.button_cd),
                        tint = MaterialTheme.colorScheme.background
                    )
                }
                Button(
                    onClick = {
                        viewModel.onEvent(AddEditTaskEvent.OnCancelAlarmClick)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 3.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_alarm_off_24),
                        contentDescription = stringResource(R.string.button_cd),
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(viewModel: AddEditTaskViewModel) {
    val dateTime = LocalDateTime.now()

    val timePickerState = remember {
        TimePickerState(
            is24Hour = true,
            initialHour = dateTime.hour,
            initialMinute = dateTime.minute
        )
    }

    TimePicker(state = timePickerState)
    viewModel.minutes = timePickerState.minute.toString()
    viewModel.hours = timePickerState.hour.toString()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(viewModel: AddEditTaskViewModel) {
    val dateTime = LocalDateTime.now()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = dateTime.toMillis(),
        initialDisplayedMonthMillis = null,
        initialDisplayMode = DisplayMode.Picker,
        yearRange = (2023..2024),
    )

    DatePicker(datePickerState)
}

fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()