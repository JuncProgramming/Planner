package com.example.planner.ui.task_add_edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.planner.util.UiEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreenLayout(
    onPopBackStack: () -> Unit, viewModel: AddEditTaskViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.action,
                            duration = SnackbarDuration.Long
                        )
                    }
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Planner") }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.background,
                navigationIconContentColor = MaterialTheme.colorScheme.background
            ), navigationIcon = {
                IconButton(
                    onClick = { },
                    modifier = Modifier.padding(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Navigate back"
                    )
                }
            })
        },
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp, vertical = 4.dp
                )
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.outline),
                value = viewModel.taskName,
                onValueChange = { viewModel.onEvent(AddEditTaskEvent.OnTaskNameChange(it)) },
                label = { Text("Task") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = MaterialTheme.colorScheme.outline),
                value = viewModel.categoryName,
                onValueChange = { viewModel.onEvent(AddEditTaskEvent.OnCategoryNameChange(it)) },
                label = { Text("Category") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )

            Button(
                onClick = { viewModel.onEvent(AddEditTaskEvent.OnSaveTodoClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Proceed",
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}