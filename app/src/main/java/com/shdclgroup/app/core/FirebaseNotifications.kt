package com.shdclgroup.app.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.shdclgroup.app.R

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        sendNotification(
            this,
            title = remoteMessage.notification?.title.toString(),
            message = remoteMessage.notification?.body.toString()
        )
        // Handle incoming FCM messages here.
    }
}


fun sendNotification(context: Context, title: String, message: String) {
    val notificationManager = ContextCompat.getSystemService(
        context, NotificationManager::class.java
    ) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "channelId", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }

    val notification = NotificationCompat.Builder(context, "channelId").setContentTitle(title)
        .setContentText(message).setSmallIcon(R.drawable.ic_launcher_background).setAutoCancel(true).build()

    notificationManager.notify(/* notificationId */ 999, notification)
}
