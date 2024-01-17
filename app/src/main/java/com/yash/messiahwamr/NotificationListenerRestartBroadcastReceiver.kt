package com.yash.messiahwamr

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class NotificationListenerRestartBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        p0?.startService(Intent(p0, CustomNotificationListenerService::class.java))
        p0?.startService(Intent(p0, MediaObservingService::class.java))


    }

}