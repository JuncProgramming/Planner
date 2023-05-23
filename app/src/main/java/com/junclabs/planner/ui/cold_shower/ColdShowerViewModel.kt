package com.junclabs.planner.ui.cold_shower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junclabs.planner.util.Routes
import com.junclabs.planner.util.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ColdShowerViewModel : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ColdShowerEvent) {
        when (event) {
            ColdShowerEvent.OnAddDays -> TODO()
            ColdShowerEvent.OnDrawerItemClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    sendUiEvent(UiEvent.Navigate(Routes.TASKSLIST.route))
                }
            }
            ColdShowerEvent.OnResetDays -> TODO()
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(event)
        }
    }
}
