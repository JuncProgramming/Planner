package com.example.planner

sealed class Screen(val route: String) {
    object TasksList : Screen(route = "tasks_list")
    object AddScreen : Screen(route = "add_screen")
    object UpdateScreen : Screen(route = "update_screen")
    object NotificationScreen : Screen(route = "notification_screen")
}
