package com.junclabs.planner.ui.task_add_edit

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junclabs.AlarmItem
import com.junclabs.planner.AndroidAlarmScheduler
import com.junclabs.planner.R
import com.junclabs.planner.TaskApp
import com.junclabs.planner.data.Task
import com.junclabs.planner.data.repository.TaskRepositoryImplementation
import com.junclabs.planner.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val repository: TaskRepositoryImplementation,
    savedStateHandle: SavedStateHandle,
    @ApplicationContext context: Context
) : ViewModel() {
    var minutes by
    mutableStateOf("")

    var hours by
    mutableStateOf("")

    var task by mutableStateOf<Task?>(null)
        private set

    var taskName by mutableStateOf("")
        private set

    var categoryName by mutableStateOf("")
        private set

    private val scheduler = AndroidAlarmScheduler(context)
    var alarmItem: AlarmItem? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val taskId = savedStateHandle.get<Int>("taskId")!!
        if (taskId != -1) {
            viewModelScope.launch {
                repository.getTaskById(taskId)?.let { task ->
                    taskName = task.taskName
                    categoryName = task.categoryName ?: ""
                    this@AddEditTaskViewModel.task = task
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is AddEditTaskEvent.OnTaskNameChange -> {
                taskName = event.taskName
            }

            is AddEditTaskEvent.OnCategoryNameChange -> {
                categoryName = event.categoryName
            }

            is AddEditTaskEvent.OnSaveTodoClick -> {
                val context = TaskApp.instance?.context
                viewModelScope.launch {
                    if (taskName.isBlank()) {
                        if (context != null) {
                            sendUiEvent(
                                UiEvent.ShowSnackbar(
                                    message = context.getString(R.string.snackbar_empty)
                                )
                            )
                        }
                        return@launch
                    }
                    repository.addTask(
                        Task(
                            taskName = taskName, categoryName = categoryName, id = task?.id
                        )
                    )
                }
            }

            AddEditTaskEvent.OnSaveAlarmClick -> {
                alarmItem = AlarmItem(time = LocalDateTime.now().plusMinutes(minutes.toLong()).plusHours(hours.toLong()), task = taskName)
                alarmItem?.let(scheduler::schedule)
            }

            AddEditTaskEvent.OnCancelAlarmClick -> {
                alarmItem?.let(scheduler::cancel)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}