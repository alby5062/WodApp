package it.alberto.wodapp.Wod.BaseWod

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.update_base_wod.*


class UpdateWod : AppCompatActivity() {

    lateinit var id: String
    lateinit var name: String
    private lateinit var type: String
    private lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_base_wod)

        //First we call this
        getAndSetIntentData()

        //Set actionbar  after getAndSetIntentData method
        supportActionBar?.title = name
        btn_update_wod.setOnClickListener {

            val myDB = DatabaseHelper(this)
            name = ed_update_name.text.toString().trim { it <= ' ' }
            type = ed_update_type.text.toString().trim { it <= ' ' }
            date = ed_update_date.text.toString().trim { it <= ' ' }
            myDB.updateData(id, name, type, date)
        }

        btn_delete_wod.setOnClickListener{
            confirmDialog()
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
            ed_update_name.setText(name)
            ed_update_type.setText(type)
            ed_update_date.setText(date)
            Log.d("stev", "$name $type $date")
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Delete $name ?")
        builder.setMessage("Are you sure you want to delete $name ?")
        builder.setPositiveButton("Yes"
        ) { _, _ ->
            val myDB = DatabaseHelper(this)
            myDB.deleteOneRow(id)
            finish()
        }
        builder.setNegativeButton("No"
        ) { _, _ -> }
        builder.create().show()
    }
}