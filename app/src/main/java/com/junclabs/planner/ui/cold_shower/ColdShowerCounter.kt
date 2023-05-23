package com.junclabs.planner.ui.cold_shower

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.junclabs.planner.R
import com.junclabs.planner.util.UiEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColdShowerScreen(
    modifier: Modifier = Modifier,
    viewModel: ColdShowerViewModel = ColdShowerViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
) {
    val items = listOf(Icons.Default.Check to "Planner", Icons.Default.DateRange to "Cold Showers")
    var selectedItem by remember { mutableStateOf(1) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = modifier.height(6.dp))
                items.forEachIndexed { index, item ->
                    Spacer(modifier = modifier.height(6.dp))
                    NavigationDrawerItem(
                        modifier = modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        icon = { Icon(item.first, contentDescription = item.second) },
                        label = { Text(item.second) },
                        selected = selectedItem == index,
                        onClick = {
                            viewModel.onEvent(ColdShowerEvent.OnDrawerItemClick)
                            selectedItem = index
                            coroutineScope.launch { drawerState.close() }
                        },
                    )
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Toggle drawer",
                                    tint = MaterialTheme.colorScheme.background
                                )

                            }
                        },
                        title = { Text(stringResource(R.string.planner_name)) },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.background
                        )
                    )
                },
            ) { padding ->
                Column(modifier = modifier.padding(padding)) {
                    Text(text = "Cold days")
                }
            }
        }
    )
}