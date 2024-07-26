package com.example.battery_app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class NotificationReciever(private val batteryLevel: MutableState<Int>):BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        if (level != -1 && scale != -1) {
            batteryLevel.value = (level * 100) / scale
        }
    }
}