package com.example.foodloverscapston2.meals

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.meals.ui.notifyList
import com.example.foodloverscapston2.view.MainActivity

class MyWorker (context: Context, workerParameters: WorkerParameters):
    Worker(context,workerParameters) {

    companion object {
        const val CHANNEL_ID = "Channel_id"
        const val NOTIFICATION_ID = 1
    }

    override fun doWork(): Result {
        Log.d("Tag", "do worker")
        showNotification()
        return Result.success()
    }

    private fun showNotification() {

        var notify = apiNotification()
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        val notification = NotificationCompat.Builder(
            applicationContext,
            CHANNEL_ID
        )
            .setSmallIcon(R.drawable.app_logo)
            .setContentTitle(notify)
            .setContentText("New Recipe To Try Don't Miss it ")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Channel name"
            val channelDescription = "Channel Description"
            val channelImport = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, channelName, channelImport).apply {
                description = channelDescription
            }

            val notificationManager = applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        with(NotificationManagerCompat.from(applicationContext)) {
            notify(NOTIFICATION_ID, notification.build())
        }
    }
    private fun apiNotification():String {

        val numberOfElement = 9
        val randomElement =
            notifyList!!.asSequence().shuffled().take(numberOfElement).toList()

        Log.e(TAG, "my msg ${randomElement[0]} ")
        return  randomElement[0].strMeal
    }
}

