package com.demo.mvvm.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.os.Build

object NotificationUtil {
    fun createNotification(context: Context, title: String?): Notification {
        val builder: Notification.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "CHANNEL_ID_1111"
            val notificationManager = context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                channelId, "CHANNEL_NAME_aaa", NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
            Notification.Builder(context, channelId)
        } else {
            Notification.Builder(context)
        }
        return builder
            .setContentTitle(title)
            .setSmallIcon(android.R.mipmap.sym_def_app_icon)
            .build()
    }
}