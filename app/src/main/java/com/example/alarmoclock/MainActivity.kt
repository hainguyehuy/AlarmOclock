package com.example.alarmoclock

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Toast
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import com.example.alarmoclock.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

private lateinit var binding: ActivityMainBinding
private var list = mutableListOf<DataClock>()
val today = Calendar.getInstance()
private lateinit var customAdapter: CustomAdapter
private var pendingIntent: PendingIntent? = null
private var alarmManager:AlarmManager? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        binding.imvAddClock.setOnClickListener {
            var intent = Intent(this, SetUpAlarm::class.java)
            startActivity(intent)
        }
        getValue()
        showItemAlarm()
        var tbOnOff = customAdapter.activity.findViewById<ToggleButton>(R.id.tbONOFF)
        tbOnOff.setOnCheckedChangeListener {_, isChecked ->
            var time:Long
            if(isChecked){
                Toast.makeText(this,"Alarm On",Toast.LENGTH_SHORT).show()

                pendingIntent = PendingIntent.getBroadcast(
                    this,
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
                Toast.makeText(this,"Alarm OFF",Toast.LENGTH_SHORT).show()

            }
        }

    }

    private fun deleteAlarm(){
        binding.lvAlarm.setOnItemClickListener { parent, view, position, id ->
            list.removeAt(position)
        }
    }

    private fun getValue() {
        var i = intent
        var itemListTime = i.getStringExtra("selectedTime")
        var itemListName = i.getStringExtra("giveName")
        list.add(DataClock("$itemListTime","$itemListName"))
    }

    private fun showItemAlarm() {
        customAdapter = CustomAdapter(this, list)
        binding.lvAlarm.adapter = customAdapter
    }



}