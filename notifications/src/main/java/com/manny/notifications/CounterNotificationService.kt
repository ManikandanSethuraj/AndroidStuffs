package com.manny.notifications

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class CounterNotificationService(
    val context : Context
) {

    fun showNotification(counter : Int){

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val activityIntent = Intent(context, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )


        val counterReceiverIntentUp = Intent(context, CounterReceiver::class.java)
        counterReceiverIntentUp.putExtra("Value","Up")
        val actionIntentUp = PendingIntent.getBroadcast(
            context,
            4,
            counterReceiverIntentUp,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val counterReceiverIntentDown = Intent(context, CounterReceiver::class.java)
        counterReceiverIntentDown.putExtra("Value","Down")
        val actionIntentDown = PendingIntent.getBroadcast(
            context,
            3,
            counterReceiverIntentDown,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val notification = NotificationCompat.Builder(context, COUNTER_CHANNEL_ID)
            .setContentTitle("CounterTitle")
            .setContentText("The counter value is $counter")
            .setSmallIcon(R.drawable.ic_baseline_baby_changing_station_24)
            .setContentIntent(pendingIntent)

            .addAction(
                R.drawable.ic_baseline_baby_changing_station_24,
                "Decrement",
                actionIntentDown
            )
            .addAction(
                R.drawable.ic_baseline_baby_changing_station_24,
                "Increment",
                actionIntentUp
            )
            .build()


        notificationManager.notify(
            1,
            notification
        )
    }

    companion object{
        const val COUNTER_CHANNEL_ID = "counter_channel"
    }
}