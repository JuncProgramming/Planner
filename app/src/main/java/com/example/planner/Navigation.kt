package com.example.planner

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.TasksList.route) {
        composable(
            route = Screen.TasksList.route
        ) {
            TasksListLayout(navController = navController)
        }
        composable(
            route = Screen.AddScreen.route
        ) {
            AddScreenLayout()
        }
        composable(
            route = Screen.UpdateScreen.route
        ) {
            UpdateScreenLayout()
        }
        composable(
            route = Screen.NotificationScreen.route
        ) {
            NotificationScreen()
        }
    }
}