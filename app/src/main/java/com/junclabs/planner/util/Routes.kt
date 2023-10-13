package com.junclabs.planner.util

sealed class Routes(val route: String) {
    object TASKSLIST : Routes(route = "tasks_list")
    object ADDEDITTASK : Routes(route = "add_edit_task")
}