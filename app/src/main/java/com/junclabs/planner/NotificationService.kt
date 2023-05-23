package com.junclabs.planner

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificationService(private val context: Context) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(task: String) {
        val activityIntent = Intent(context, MainActivity::class.java).apply {  }
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
            val notification = NotificationCompat.Builder (context, TASK_CHANNEL_ID)
        .setSmallIcon(R.drawable.baseline_offline_bolt_24)
            .setContentTitle("$task reminder")
            .setContentText("Your task $task is ending soon. Good luck completing it!")
            .setContentIntent(activityPendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

        notificationManager.notify(
            1, notification
        )
    }

    companion object {
        const val TASK_CHANNEL_ID = "task_channel"
    }
}