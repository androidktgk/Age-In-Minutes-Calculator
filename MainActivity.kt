package com.govind8061. ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var tvSelectedDate:TextView?=null
    var tvAgeInMinutes:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker=findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)
    }

    @SuppressLint("SetTextI18n")
    fun clickDatePicker(){
        val myCalander=Calendar.getInstance()
        val year=myCalander.get(Calendar.YEAR)
        val month=myCalander.get(Calendar.MONTH)
        val day=myCalander.get(Calendar.DAY_OF_MONTH)

        //Date Picker Dialog
        val dpd=DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
            Toast.makeText(this,"Date Selected",Toast.LENGTH_SHORT).show()
            val selectedDate="$day/${month+1}/$year"
            tvSelectedDate?.text=selectedDate

            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val dateFormat=sdf.parse(selectedDate)
            dateFormat?.let {
                val selectedDateInMinutes=dateFormat.time/60000

                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let{
                    val currentDateInMinutes=currentDate.time/60000

                    val differenceDateInMinutes=currentDateInMinutes-selectedDateInMinutes
                    
                    tvAgeInMinutes?.text=differenceDateInMinutes.toString()

                }

            }

        },year,month,day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-(24*60*60*1000)
        dpd.show()

    }
}