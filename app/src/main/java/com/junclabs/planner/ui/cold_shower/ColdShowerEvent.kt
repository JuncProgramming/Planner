package com.junclabs.planner.ui.cold_shower

sealed class ColdShowerEvent {
    object OnDrawerItemClick: ColdShowerEvent()
    object OnAddDays: ColdShowerEvent()
    object OnResetDays: ColdShowerEvent()
}