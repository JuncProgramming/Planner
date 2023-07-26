package com.junclabs.planner

import android.app.NotificationManager
import android.content.Context

class NotificationService(private val context: Context) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val TASK_CHANNEL_ID = "task_channel"
    }
}