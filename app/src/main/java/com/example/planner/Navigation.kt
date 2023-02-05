package com.example.planner

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.planner.ui.task_add_edit.AddEditScreenLayout
import com.example.planner.ui.task_list.TasksListLayout
import com.example.planner.util.Routes

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.TASKS_LIST) {
        composable(
            Routes.TASKS_LIST
        ) {
            TasksListLayout(onNavigate = {
                navController.navigate(it.route)
            })
        }
        composable(
            route = Routes.ADD_EDIT_TASK + "?taskId={taskId}",
            arguments = listOf(navArgument(name = "taskId") {
                type = NavType.IntType
                defaultValue = -1
            })
        ) {
            AddEditScreenLayout(onPopBackStack = {
                navController.popBackStack()
            })
        }
    }
}