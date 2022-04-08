package com.example.agetomincalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var dateTextView: TextView? = null
    private var minutesTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerButton: Button = findViewById(R.id.btnDatePicker)
        this.dateTextView = findViewById(R.id.dateView)
        this.minutesTextView = findViewById(R.id.minutesView)

        datePickerButton.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {
        val myCalendar: Calendar = Calendar.getInstance()
        val year: Int = myCalendar.get(Calendar.YEAR)
        val month: Int = myCalendar.get(Calendar.MONTH)
        val day: Int = myCalendar.get(Calendar.DAY_OF_MONTH)
        var selectedDate: String

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "${selectedMonth + 1}/$selectedDay/$selectedYear"
                this.dateTextView?.setText(selectedDate)
                val dateInMinutes : Date? = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
                    .parse(selectedDate)
                var dateDifference : Long = Date().time - (dateInMinutes?.time ?: 0)
                dateDifference = TimeUnit.MINUTES.convert(dateDifference, TimeUnit.MILLISECONDS)
                this.minutesTextView?.setText(dateDifference.toString())
            },
            year,
            month,
            day
        ).show()
    }
}