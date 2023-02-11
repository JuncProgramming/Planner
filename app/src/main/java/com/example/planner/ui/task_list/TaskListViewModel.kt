package com.example.planner.ui.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner.R
import com.example.planner.TaskApp
import com.example.planner.data.Task
import com.example.planner.data.repository.TaskRepositoryImplementation
import com.example.planner.util.Routes
import com.example.planner.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(private val repository: TaskRepositoryImplementation) :
    ViewModel() {

    val tasks = repository.getTasks()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTask: Task? = null

    fun onEvent(event: TaskListEvent) {
        when (event) {
            is TaskListEvent.OnAddTask -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TASK))
            }
            is TaskListEvent.OnEditClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TASK + "?taskId=${event.task.id}"))
            }
            is TaskListEvent.OnDeleteTask -> {
                val context = TaskApp.instance?.context
                viewModelScope.launch(Dispatchers.IO) {
                    deletedTask = event.task
                    repository.deleteTask(event.task)
                    if (context != null) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = context.getString(R.string.snackbar_deleted),
                                action = context.getString(R.string.snackbar_action)
                            )
                        )
                    }
                }

            }
            is TaskListEvent.OnUndoDeleteTask -> {
                deletedTask?.let { task ->
                    viewModelScope.launch {
                        repository.addTask(task)
                    }
                }

            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(event)
        }
    }

}
