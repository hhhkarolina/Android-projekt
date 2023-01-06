package com.example.projekt

import android.app.AlarmManager
import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlantApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        setAlarm(this)
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                PlantNotificationService.CHANNEL_ID,
                "plant",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "watering reminder"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun setAlarm(context: Context) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        /*alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 1000 * 30, pendingIntent)*/

        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 1000 * 30,
            1000 * 10,
            pendingIntent

        )
    }
}