package com.example.alarmoclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Vibrator
import android.widget.Toast
import androidx.core.content.getSystemService

class AlarmReceiver :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
//        val i = Intent(context,QuestionToWu::class.java)
//        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(1000)
        Toast.makeText(context,"ALARM WAKEUP",Toast.LENGTH_SHORT).show()
        var alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        if(alarmUri == null){
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }
        val ringtone = RingtoneManager.getRingtone(context,alarmUri)
        ringtone.play()
    }
}