package com.example.planner.ui.task_list

import com.example.planner.data.Task

sealed class TaskListEvent {
    data class OnDeleteTask(val task: Task) : TaskListEvent()
    object OnUndoDeleteTask : TaskListEvent()
    data class OnEditClick(val task: Task) : TaskListEvent()
    object OnAddTask : TaskListEvent()
}
