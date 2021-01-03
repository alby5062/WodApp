package it.alberto.wodapp.Wod.UserWod

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.calendar_activity.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)

        val events: MutableList<EventDay> = ArrayList()

        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                val clickedDayCalendar: Calendar = eventDay.calendar

                events.add(EventDay(clickedDayCalendar, R.drawable.ic_dumbbell))
                calendarView.setEvents(events)

                println(eventDay)

                //var my_date_format = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
                var my_date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                //val date = "" + clickedDayCalendar.get(Calendar.DAY_OF_MONTH) + "/" + clickedDayCalendar.get(Calendar.MONTH + 1) + "/" + clickedDayCalendar.get(Calendar.YEAR)

                //Toast.makeText(this@CalendarActivity, "", Toast.LENGTH_LONG).show()

                //val intent = Intent(this@CalendarActivity, UserWodActivity::class.java)
                //intent.putExtra("my_date", date)
                //startActivity(intent)
            }
        })
    }
}