package com.junclabs.planner.ui.task_add_edit

sealed class AddEditTaskEvent {
    data class OnTaskNameChange(val taskName: String) : AddEditTaskEvent()
    data class OnCategoryNameChange(val categoryName: String) : AddEditTaskEvent()
    object OnSaveTodoClick : AddEditTaskEvent()
    object OnSaveAlarmClick : AddEditTaskEvent()
    object OnCancelAlarmClick : AddEditTaskEvent()
}
