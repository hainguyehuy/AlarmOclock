package com.example.alarmoclock

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alarmoclock.databinding.ActivityMainBinding
import java.util.Calendar

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding
private var list = mutableListOf<DataClock>()
val today: Calendar = Calendar.getInstance()
@SuppressLint("StaticFieldLeak")
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
            val intent = Intent(this, SetUpAlarm::class.java)
            startActivity(intent)
        }
        getValue()
        showItemAlarm()
    }
    private fun getValue() {
        val i = intent
        val itemListTime = i.getStringExtra("selectedTime")
        val itemListName = i.getStringExtra("giveName")
        list.add(DataClock("$itemListTime", "$itemListName"))
    }

    private fun showItemAlarm() {
        customAdapter = CustomAdapter(this, list)
        binding.lvAlarm.adapter = customAdapter
    }
}