package com.example.alarmoclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.RingtoneManager
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.getSystemService

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(1000)
        Toast.makeText(context, "ALARM WAKEUP", Toast.LENGTH_SHORT).show()
        var alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }
        val ringtone = RingtoneManager.getRingtone(context, alarmUri)
        ringtone?.play()


    }
}