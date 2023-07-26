package com.junclabs.planner

import android.Manifest
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class BroadcastReceiver: BroadcastReceiver() {
    private var notificationManager: NotificationManagerCompat? = null
    private lateinit var notificationManagerLateinit: NotificationManagerCompat

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onReceive(context: Context, intent: Intent?) {
        val task = intent?.getStringExtra("TASK") ?: return

        val activityIntent = Intent(context, MainActivity::class.java)
        activityIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE
        )

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling ActivityCompat#requestPermissions here to request the missing permissions, and then overriding public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) to handle the case where the user grants the permission. See the documentation for ActivityCompat#requestPermissions for more details.
            return
        } else {
            context.let {
                val notification =
                    NotificationCompat.Builder(it, NotificationService.TASK_CHANNEL_ID)
                        .setSmallIcon(R.drawable.baseline_offline_bolt_24)
                        .setContentTitle("$task reminder")
                        .setContentText("Your task $task is ending soon. Good luck completing it!")
                        .setContentIntent(activityPendingIntent)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .build()
                // If you define notificationManager needs to be nullable, this should work (though I'm not sure it's best practice)
                notificationManager = NotificationManagerCompat.from(it).apply {
                    notify(1, notification)
                }
                // If notificationManger is a lateinit var
                notificationManagerLateinit = NotificationManagerCompat.from(it)
                notificationManagerLateinit.notify(1, notification)
            }
        }
    }

}