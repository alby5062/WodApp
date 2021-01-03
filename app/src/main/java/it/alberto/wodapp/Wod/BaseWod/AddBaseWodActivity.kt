package it.alberto.wodapp.Wod.BaseWod

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.add_base_wod.*


class AddBaseWodActivity : AppCompatActivity() {

    lateinit var id: String
    lateinit var name: String
    private lateinit var type: String
    private lateinit var date: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_base_wod)

        getAndSetIntentData()

        btn_add_wod.setOnClickListener {
            val myDB = DatabaseHelper(this)
            name = ed_add_name.text.toString().trim { it <= ' ' }
            type = ed_add_type.text.toString().trim { it <= ' ' }
            date = ed_add_date.text.toString().trim { it <= ' ' }
            myDB.add(name, type, date)
        }
    }

    private fun getAndSetIntentData() {
        if (intent.hasExtra("id") && intent.hasExtra("name") &&
            intent.hasExtra("type") && intent.hasExtra("date")
        ) {
            //Getting Data from Intent
            id = intent.getStringExtra("id").toString()
            name = intent.getStringExtra("name").toString()
            type = intent.getStringExtra("type").toString()
            date = intent.getStringExtra("date").toString()

            //Setting Intent Data
            ed_add_name.setText(name)
            ed_add_type.setText(type)
            ed_add_date.setText(date)
            Log.d("stev", "$name $type $date")
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
        }
    }
}