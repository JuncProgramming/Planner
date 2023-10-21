package com.junclabs.planner.ui.task_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.junclabs.planner.R
import com.junclabs.planner.util.SnackBarController
import com.junclabs.planner.ui.theme.RoundedShapes
import com.junclabs.planner.util.UiEvent
import com.junclabs.planner.navigation.items
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksListScreen(
    modifier: Modifier = Modifier,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val tasks = viewModel.tasks.collectAsState(initial = emptyList())
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val snackBarController = SnackBarController(coroutineScope)
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = snackBarController.showSnackBar(
                        message = event.message,
                        actionLabel = event.action,
                        duration = SnackbarDuration.Long,
                        snackBarHostState = snackbarHostState
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TaskListEvent.OnUndoDeleteTask)
                    }
                }

                is UiEvent.Navigate -> {
                    //if you want to hide the snackBar
                    snackBarController.cancelActiveJob()
                    onNavigate(event)
                }

                else -> Unit
            }
        }
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
            items.forEachIndexed { index, item ->
                Spacer(modifier = Modifier.height(12.dp))
                NavigationDrawerItem(
                    label = { Text(item.title) },
                    selected = index == selectedItemIndex,
                    onClick = {
                        viewModel.onEvent(TaskListEvent.OnDrawerNavigationClick(item))
                        selectedItemIndex = index
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon, contentDescription = item.title
                        )
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
            }
        }
    }, drawerState = drawerState) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(snackbarHostState) { data ->
                    Snackbar(
                        shape = RoundedShapes.medium,
                        actionColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background,
                        snackbarData = data
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    shape = RoundedShapes.medium,
                    onClick = {
                        viewModel.onEvent(TaskListEvent.OnAddTask)
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.fab_cd)
                    )

                }
            },
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.planner_name)) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.background
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
        ) { padding ->
            LazyColumn(
                state = rememberLazyListState(),
                verticalArrangement = spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp),
            ) {

                items(items = tasks.value, key = { task -> task.hashCode() }) { task ->
                    val currentTask by rememberUpdatedState(newValue = task)
                    val dismissState = rememberDismissState(confirmValueChange = {
                        if (it == DismissValue.DismissedToStart) {
                            viewModel.onEvent(TaskListEvent.OnDeleteTask(currentTask))
                        }
                        true
                    })
                    SwipeToDismiss(state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        background = { },
                        dismissContent = {
                            TaskItem(
                                task = task, modifier = modifier
                            )
                        })
                }
            }
        }
    }
}

