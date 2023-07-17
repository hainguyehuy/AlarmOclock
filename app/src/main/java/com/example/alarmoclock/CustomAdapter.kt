package com.example.alarmoclock

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import java.util.Calendar

class CustomAdapter(val activity: Activity, val list: MutableList<DataClock>) :ArrayAdapter<DataClock>(activity,R.layout.layout_custom_time) {
    override fun getCount(): Int {
        return list.size
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var contexts = activity.layoutInflater
        var rowView = contexts.inflate(R.layout.layout_custom_time,parent,false)
        var tvTime = rowView.findViewById<TextView>(R.id.tvTime)
        var tvNameAlarm = rowView.findViewById<TextView>(R.id.tvNameAlarm)
        var tbOnOff = rowView.findViewById<ToggleButton>(R.id.tbONOFF)

        tvTime.setText(list[position].time)
        tvNameAlarm.setText(list[position].nameAlarm)
        var intent = Intent(activity,AlarmReceiver::class.java)

        tbOnOff.setOnCheckedChangeListener {_, isChecked ->
            var time:Long
            if(isChecked){
                Toast.makeText(activity,"Alarm On", Toast.LENGTH_SHORT).show()
                pendingIntent = PendingIntent.getBroadcast(activity,
                    0,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE
                )
                time = today.timeInMillis - today.timeInMillis % 60000
                if(System.currentTimeMillis() > time ){
                    time = if (Calendar.AM_PM == 0 ){
                        time + 1000*60*60*12
                    }
                    else{
                        time + 1000*60*60*24
                    }
                }
                alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP,time,1000, pendingIntent!!)

            }
            else {
                alarmManager!!.cancel(pendingIntent!!)
                Toast.makeText(activity,"Alarm OFF", Toast.LENGTH_SHORT).show()

            }
        }

        return rowView
    }


}