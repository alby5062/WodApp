package it.alberto.wodapp.Wod.UserWod

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.update_user_wod.*

class UpdateUserWod : AppCompatActivity() {

    lateinit var id: String
    lateinit var name: String
    private lateinit var type: String
    private lateinit var date: String
    private lateinit var exercises: String
    private lateinit var result: String

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_user_wod)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val picker = findViewById<View>(R.id.datePicker) as DatePicker

        getAndSetIntentData()

        title = "Modify $name"

        btn_update_wod.setOnClickListener {

            val date_pik = picker.dayOfMonth.toString() + "/" + (picker.month + 1).toString() + "/" + picker.year

            val myDB = DatabaseHelper(this)
            name = ed_update_name.text.toString().trim { it <= ' ' }
            type = ed_update_type.text.toString().trim { it <= ' ' }
            date = date_pik.trim { it <= ' ' }
            result = ed_update_result.text.toString().trim { it <= ' ' }
            myDB.updateData(id, name, type, date, result)
            onBackPressed()
        }

        btn_delete_wod.setOnClickListener{
            confirmDialog()
        }

        constraint_update_user.setOnTouchListener{ _, _ ->
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            true
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
            exercises = intent.getStringExtra("exercises").toString()
            result = intent.getStringExtra("result").toString()

            val mex = exercises.replace("\\n", "\n")

            //Setting Intent Data
            ed_update_name.setText(name)
            ed_update_type.setText(type)
            ed_update_result.setText(result)
            //ed_update_date.setText(date)
            ed_update_ex.text = mex
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
            //val intent = Intent(this, UserWodActivity::class.java)
            //intent.putExtra("my_date", date)
            //startActivity(intent)
            onBackPressed()
        }
        builder.setNegativeButton("No"
        ) { _, _ -> }
        builder.create().show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, UserWodActivity::class.java)
        //intent.putExtra("my_date", date)
        //startActivityForResult(intent, 1)
        //intent.putExtra("my_date", date)
        setResult(1, intent)
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