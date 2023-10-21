package com.junclabs.planner.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

val items = listOf(
    NavigationItem(
        title = "Planner",
        selectedIcon = Icons.Default.Done,
        unselectedIcon = Icons.Default.Done,
        route = Routes.TASKSLIST.route
    ),
    NavigationItem(
        title = "Library",
        selectedIcon = Icons.Default.Email,
        unselectedIcon = Icons.Default.Email,
        route = Routes.ADDEDITTASK.route
    )
)