package com.junclabs.planner

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val context: Context
        get() = this

    companion object {
        var instance: TaskApp? = null
            private set
    }
}