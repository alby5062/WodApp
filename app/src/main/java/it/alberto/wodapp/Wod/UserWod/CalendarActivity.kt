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
        onBackPressed()
        return true
    }
}