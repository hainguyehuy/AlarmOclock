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
var pendingIntent: PendingIntent? = null
var alarmManager: AlarmManager? = null

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
    }
    private fun getValue() {
        var i = intent
        var itemListTime = i.getStringExtra("selectedTime")
        var itemListName = i.getStringExtra("giveName")
        list.add(DataClock("$itemListTime", "$itemListName"))
    }

    private fun showItemAlarm() {
        customAdapter = CustomAdapter(this, list)
        binding.lvAlarm.adapter = customAdapter
    }
}