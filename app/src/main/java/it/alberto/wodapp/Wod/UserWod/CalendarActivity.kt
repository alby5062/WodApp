package it.alberto.wodapp.Wod.UserWod

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.MainActivity
import it.alberto.wodapp.R


class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvw = findViewById<View>(R.id.textView1) as TextView
        val picker = findViewById<View>(R.id.datePicker) as DatePicker
        val btnGet = findViewById<View>(R.id.button1) as Button
        btnGet.setOnClickListener {
            val date =
                picker.dayOfMonth.toString() + "/" + (picker.month + 1).toString() + "/" + picker.year
            tvw.text = date

            val intent = Intent(this, UserWodActivity::class.java)
            intent.putExtra("my_date", date)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /*override fun onStart() {
      super.onStart()
      overridePendingTransition(
          R.anim.slide_in_right,
          R.anim.slide_out_left
      )
  }

  override fun onBackPressed() {
      super.onBackPressed()
      overridePendingTransition(
              R.anim.slide_in_left,
              R.anim.slide_out_right
      )
  }*/

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