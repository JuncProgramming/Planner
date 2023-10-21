package com.junclabs.planner.ui.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junclabs.planner.R
import com.junclabs.planner.TaskApp
import com.junclabs.planner.data.Task
import com.junclabs.planner.data.repository.TaskRepositoryImplementation
import com.junclabs.planner.navigation.Routes
import com.junclabs.planner.util.UiEvent
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
                viewModelScope.launch(Dispatchers.IO) {
                    sendUiEvent(UiEvent.Navigate(Routes.ADDEDITTASK.route))
                }
            }

            is TaskListEvent.OnEditClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADDEDITTASK.route + "?taskId=${event.task.id}"))
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
                    viewModelScope.launch(Dispatchers.IO) {
                        repository.addTask(task)
                    }
                }

            }
            is TaskListEvent.OnDrawerNavigationClick -> {
                sendUiEvent(UiEvent.Navigate(event.navigationItem.route))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(event)
        }
    }
}
