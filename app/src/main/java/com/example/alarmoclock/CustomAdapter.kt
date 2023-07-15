package com.example.alarmoclock

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton

class CustomAdapter(val activity: Activity, val list: MutableList<DataClock>) :ArrayAdapter<DataClock>(activity,R.layout.layout_custom_time) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var contexts = activity.layoutInflater
        var rowView = contexts.inflate(R.layout.layout_custom_time,parent,false)
        var tvTime = rowView.findViewById<TextView>(R.id.tvTime)
        var tvNameAlarm = rowView.findViewById<TextView>(R.id.tvNameAlarm)

        tvTime.setText(list[position].time)
        tvNameAlarm.setText(list[position].nameAlarm)
        return rowView
    }


}