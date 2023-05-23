package com.junclabs.planner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("Message") ?: return
        println("Alarm triggered: $message")
    }

}