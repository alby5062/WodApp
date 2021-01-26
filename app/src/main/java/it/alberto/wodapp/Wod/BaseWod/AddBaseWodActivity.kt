package it.alberto.wodapp.Wod.BaseWod

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.InputCheck
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.UserWod.UserWodActivity
import kotlinx.android.synthetic.main.add_base_wod.*


class AddBaseWodActivity : AppCompatActivity() {

    private lateinit var id: String
    private lateinit var name: String
    private lateinit var type: String
    private lateinit var date: String
    private lateinit var exercises: String
    private lateinit var result: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_base_wod)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val picker = findViewById<View>(R.id.datePicker) as DatePicker
        val date_pik = picker.dayOfMonth.toString() + "/" + (picker.month + 1).toString() + "/" + picker.year

        getAndSetIntentData()

        btn_add_wod.setOnClickListener {

            val result_ed = ed_add_result.text.toString()
            if (InputCheck().inputResult(result_ed)) {

                val myDB = DatabaseHelper(this)
                name = ed_add_name.text.toString().trim { it <= ' ' }
                type = ed_add_type.text.toString().trim { it <= ' ' }
                date = date_pik.trim { it <= ' ' }
                exercises = ed_add_ex.text.toString().trim { it <= ' ' }
                result = ed_add_result.text.toString().trim { it <= ' ' }
                myDB.add(name, type, date, exercises, result)

                val intent = Intent(this, UserWodActivity::class.java)
                intent.putExtra("my_date", date)
                startActivity(intent)

                finish()
                overridePendingTransition(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
            } else {
                Toast.makeText(this, "Insert result", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getAndSetIntentData() {
        if (intent.hasExtra("id") && intent.hasExtra("name") &&
            intent.hasExtra("type") && intent.hasExtra("date") &&
            intent.hasExtra("exercises")
        ) {
            //Getting Data from Intent
            id = intent.getStringExtra("id").toString()
            name = intent.getStringExtra("name").toString()
            type = intent.getStringExtra("type").toString()
            date = intent.getStringExtra("date").toString()
            exercises = intent.getStringExtra("exercises").toString()
            result = intent.getStringExtra("result").toString()

            //Setting Intent Data
            ed_add_name.setText(name)
            ed_add_type.setText(type)
            ed_add_ex.text = exercises
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
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