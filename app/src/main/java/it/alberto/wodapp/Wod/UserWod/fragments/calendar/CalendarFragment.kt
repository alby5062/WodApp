package it.alberto.wodapp.Wod.UserWod.fragments.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import it.alberto.wodapp.R
import java.util.*


class CalendarFragment : Fragment() {

    private lateinit var calendarView: CalendarView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        calendarView = view.findViewById(R.id.calendarView)

        /*val pm = requireContext().packageManager
        val appInfo = pm.getApplicationInfo("it.alberto.wodapp", 0)
        val appFile = appInfo.sourceDir
        val installed: Long = File(appFile).lastModified()
        calendarView.setMinimumDate()*/


        val events: MutableList<EventDay> = ArrayList()

        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                val clickedDayCalendar: Calendar = eventDay.calendar

                events.add(EventDay(clickedDayCalendar, R.drawable.ic_dumbbell))
                calendarView.setEvents(events)

                //var my_date_format: SimpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
                //var my_date: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                //val date = "" + clickedDayCalendar.get(Calendar.DAY_OF_MONTH) + "/" + clickedDayCalendar.get(Calendar.MONTH) + "/" + clickedDayCalendar.get(Calendar.YEAR)


               /* val intent = Intent(activity, UserWodActivity::class.java)
                intent.putExtra("date", date)
                startActivity(intent)*/

                Toast.makeText(activity, "" + clickedDayCalendar.get(Calendar.DAY_OF_MONTH) + "/" + clickedDayCalendar.get(Calendar.MONTH + 1) + "/" + clickedDayCalendar.get(Calendar.YEAR),
                        Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_calendarFragment_to_listUserWodFragment)
            }
        })
        return view
    }
}