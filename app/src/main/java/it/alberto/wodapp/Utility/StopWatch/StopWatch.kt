package it.alberto.wodapp.Utility.StopWatch

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import it.alberto.wodapp.R


class StopWatch : AppCompatActivity() {

    lateinit var textView: TextView
    private lateinit var start: FloatingActionButton
    private lateinit var pause: FloatingActionButton
    private lateinit var reset: FloatingActionButton
    private lateinit var lap: Button

    var MillisecondTime: Long = 0
    var StartTime: Long = 0
    var TimeBuff: Long = 0
    var UpdateTime = 0L
    var handler: Handler? = null
    var Seconds = 0
    var Minutes = 0
    var MilliSeconds = 0

    var listView: ListView? = null
    var ListElements = arrayOf<String>()
    var ListElementsArrayList: MutableList<String>? = null
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop_watch)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Chronometer"

        textView = findViewById<View>(R.id.textView) as TextView
        start = findViewById<View>(R.id.button) as FloatingActionButton
        pause = findViewById<View>(R.id.button2) as FloatingActionButton
        reset = findViewById<View>(R.id.button3) as FloatingActionButton
        lap = findViewById<View>(R.id.button4) as Button
        listView = findViewById<View>(R.id.listview1) as ListView

        handler = Handler()

        ListElementsArrayList = ArrayList(arrayOf(*ListElements))

        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            ListElementsArrayList!!
        )
        listView!!.adapter = adapter

        start.setOnClickListener{
            StartTime = SystemClock.uptimeMillis()
            handler!!.postDelayed(runnable, 0)
            reset.isEnabled = false
        }

        pause.setOnClickListener{
            TimeBuff += MillisecondTime
            handler!!.removeCallbacks(runnable)
            reset.isEnabled = true
        }

        reset.setOnClickListener{
            MillisecondTime = 0L
            StartTime = 0L
            TimeBuff = 0L
            UpdateTime = 0L
            Seconds = 0
            Minutes = 0
            MilliSeconds = 0
            textView.text = "00:00:00"
            ListElementsArrayList!!.clear()
            adapter!!.notifyDataSetChanged()
        }

        lap.setOnClickListener{
            ListElementsArrayList!!.add(textView.text.toString())
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun <T> ArrayList(arrayOf: Array<T>): MutableList<String> {
        return ArrayList()
    }

    private var runnable: Runnable = object : Runnable {
        override fun run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime
            UpdateTime = TimeBuff + MillisecondTime
            Seconds = (UpdateTime / 1000).toInt()
            Minutes = Seconds / 60
            Seconds %= 60
            MilliSeconds = (UpdateTime % 1000).toInt()
            textView.text = ("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds))
            handler?.postDelayed(this, 0)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}