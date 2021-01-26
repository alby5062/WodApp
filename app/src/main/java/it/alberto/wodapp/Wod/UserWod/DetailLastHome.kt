package it.alberto.wodapp.Wod.UserWod

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.MainActivity
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.detail_last_card.*

class DetailLastHome : AppCompatActivity() {

    private var myDB: DatabaseHelper? = null
    private lateinit var id: String
    private lateinit var name: String
    private lateinit var type:String
    private lateinit var date:String
    private lateinit var result: String
    private  lateinit var exercise: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_last_home)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        myDB = DatabaseHelper(this)
        id = String()
        name = String()
        type = String()
        date = String()
        result = String()
        exercise = String()

        takeLastDetail()

        title = name

        detail_last_name.text = name
        detail_last_type.text = type
        detail_last_date.text = date
        detail_last_result.text = result
        detail_last_exercise.text = exercise

        btn_detail_last.setOnClickListener {
            confirmDialog()
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
            startActivity(Intent(this, MainActivity::class.java))
        }
        builder.setNegativeButton("No"
        ) { _, _ -> }
        builder.create().show()
    }

    private fun takeLastDetail(){
        val cursor: Cursor? = myDB!!.takeLastUpload()
        if (cursor != null) {

            while (cursor.moveToNext()){
                id = (cursor.getString(0))
                name = (cursor.getString(1))
                type = (cursor.getString(2))
                date = (cursor.getString(3))
                exercise = (cursor.getString(4))
                result = (cursor.getString(5))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}