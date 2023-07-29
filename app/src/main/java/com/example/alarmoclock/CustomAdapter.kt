package com.example.alarmoclock

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog

class CustomAdapter(private val activity: Activity, private val list: MutableList<DataClock>) :
    ArrayAdapter<DataClock>(activity, R.layout.layout_custom_time) {
    override fun getCount(): Int {
        return list.size
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contexts = activity.layoutInflater
        val rowView = contexts.inflate(R.layout.layout_custom_time, parent, false)
        val tvTime = rowView.findViewById<TextView>(R.id.tvTime)
        val tvNameAlarm = rowView.findViewById<TextView>(R.id.tvNameAlarm)
        val tbOnOff = rowView.findViewById<ToggleButton>(R.id.tbONOFF)

        tvTime.text = list[position].time
        tvNameAlarm.text = list[position].nameAlarm
        val intent = Intent(activity, AlarmReceiver::class.java)

        tbOnOff.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
//                val dialog = AlertDialog.Builder(activity)
//                dialog.apply {
//                    setTitle("Confirm choose")
//                    setMessage("Do you want to  set an alarm?")
//                    setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
//                        dialogInterface.dismiss()
//                    }
//                    setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(activity, "Alarm on", Toast.LENGTH_SHORT).show()
                alarmManager =
                    context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                pendingIntent = PendingIntent.getBroadcast(
                    context,
                    1,
                    intent,
                    PendingIntent.FLAG_MUTABLE
                )
                alarmManager!!.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    today.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent!!
                )

//                    }
//                }
//                dialog.show()
            }
        }

        return rowView
    }


}