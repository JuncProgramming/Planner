package com.junclabs.planner.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.junclabs.planner.ui.task_add_edit.AddEditScreen
import com.junclabs.planner.ui.task_list.TasksListScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.TASKSLIST.route) {
        composable(
            Routes.TASKSLIST.route
        ) {
            TasksListScreen(onNavigate = {
                navController.navigate(it.route)
            })
        }
        composable(
            route = Routes.ADDEDITTASK.route + "?taskId={taskId}",
            arguments = listOf(navArgument(name = "taskId") {
                type = NavType.IntType
                defaultValue = -1
            })
        ) {
            AddEditScreen(
                onPopBackStack = {
                    navController.popBackStack()
                })
        }
    }
}