package com.example.alarmoclock

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class SetUpAlarm : AppCompatActivity() {
    lateinit var btnOk: Button
    lateinit var btnCancel: Button
    lateinit var timePickerDialog: TimePicker
    lateinit var tvTime: TextView
    lateinit var edtNameAlarm: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_up_alarm)

        btnOk = findViewById(R.id.btnOk)
        btnCancel = findViewById(R.id.btnCancel)
        timePickerDialog = findViewById(R.id.timePickerDialog)
        tvTime = findViewById(R.id.tvTime)
        edtNameAlarm = findViewById(R.id.edtNameAlarm)

        installTime()
        btnCancel.setOnClickListener {
            cancelMain()
        }
    }

    private fun cancelMain() {
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    private fun installTime() {
        timePickerDialog.setOnTimeChangedListener { view, hourOfDay, minute ->
                btnOk.setOnClickListener {
                    var msg = "$hourOfDay : $minute"
                    tvTime.text = "You installed $msg + ${edtNameAlarm.text}"
                    var intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("selectedTime","$msg")
                    intent.putExtra("giveName","${edtNameAlarm.text}")
                    startActivity(intent)
                }
            }

    }
}