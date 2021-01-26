package it.alberto.wodapp.Wod.UserWod

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.MainActivity
import it.alberto.wodapp.R
import java.util.*


class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val picker = findViewById<View>(R.id.datePicker) as DatePicker
        val btnGet = findViewById<View>(R.id.button1) as Button

        val today = Calendar.getInstance()
        picker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { _, year, month, day ->
            val month = month + 1

            val counter = DatabaseHelper(this).takeCountWod("$day/$month/$year")

            val msg = "There are: $counter wods"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        btnGet.setOnClickListener {
            val date =
                picker.dayOfMonth.toString() + "/" + (picker.month + 1).toString() + "/" + picker.year

            val intent = Intent(this, UserWodActivity::class.java)
            intent.putExtra("my_date", date)
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        return true
    }
}

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)

        val events: MutableList<EventDay> = ArrayList()

        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                val clickedDayCalendar: Calendar = eventDay.calendar
                events.add(EventDay(clickedDayCalendar, R.drawable.ic_dumbbell))
                calendarView.setEvents(events)

                nextActivity()

            }
        })

        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                val clickedDayCalendar: Calendar = eventDay.calendar

                events.add(EventDay(clickedDayCalendar, R.drawable.ic_dumbbell))
                calendarView.setEvents(events)

                println(eventDay)

                //var my_date_format = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
                var my_date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                val day = Calendar.DAY_OF_MONTH
                //val month =
                val year = Calendar.YEAR






                //val date = "" + clickedDayCalendar.get(day) + "/" + (clickedDayCalendar.get(month + 1)) + "/" + clickedDayCalendar.get( year)

                //Toast.makeText(this@CalendarActivity, date, Toast.LENGTH_LONG).show()

                //val intent = Intent(this@CalendarActivity, UserWodActivity::class.java)
                //intent.putExtra("my_date", date)
                //startActivity(intent)
            }
        })*/