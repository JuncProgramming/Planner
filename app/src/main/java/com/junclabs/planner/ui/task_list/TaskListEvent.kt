package com.junclabs.planner.ui.task_list

import com.junclabs.planner.data.Task

sealed class TaskListEvent {
    data class OnDeleteTask(val task: Task) : TaskListEvent()
    object OnUndoDeleteTask : TaskListEvent()
    data class OnEditClick(val task: Task) : TaskListEvent()
    object OnAddTask : TaskListEvent()
}
