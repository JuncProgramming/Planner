package com.example.planner

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.TasksList.route)
    {
        composable(
            route = Screen.TasksList.route
        ) {
            TasksList(navController = navController)
        }
        composable(
            route = Screen.AddScreen.route
        ) {
            AddScreen()
        }
        composable(
            route = Screen.UpdateScreen.route
        ) {
            UpdateScreen()
        }
    }
}