package com.manny.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.manny.notifications.Counter.value

class CounterReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {

        val counterNotificationService = CounterNotificationService(context)
        if (intent != null) {
            Log.d("Counter","Comes1")
            if (intent.hasExtra("Value")){
                Log.d("Counter","Comes2")
                val extra = intent.getStringExtra("Value")
                if (extra.equals("Up")){
                    Log.d("Counter","Comes3")

                    counterNotificationService.showNotification(++value)
                }else {
                    Log.d("Counter","Comes4")

//                    if (value == 0){
//                        counterNotificationService.showNotification(value)
//                    }else{
                        counterNotificationService.showNotification(--value)
                //    }
                }
            }

        }

    }
}