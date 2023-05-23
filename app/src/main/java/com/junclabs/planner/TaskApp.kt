package com.junclabs.planner

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationService.TASK_CHANNEL_ID,
            "Tasks",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "Used for tasks' notifications"

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    val context: Context
        get() = this

    companion object {
        var instance: TaskApp? = null
            private set
    }
}