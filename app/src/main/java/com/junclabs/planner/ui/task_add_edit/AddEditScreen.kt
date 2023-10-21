package com.junclabs.planner.ui.task_add_edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junclabs.planner.R
import com.junclabs.planner.util.SnackBarController
import com.junclabs.planner.ui.theme.RoundedShapes
import com.junclabs.planner.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    onPopBackStack: () -> Unit, viewModel: AddEditTaskViewModel = hiltViewModel()
) {
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
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.navigationButton_cd)
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
                onClick = { viewModel.onEvent(AddEditTaskEvent.OnSaveTodoClick) },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = stringResource(R.string.button_cd),
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}