package it.alberto.wodapp.Wod.UserWod

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.MainActivity
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.add_user_wod.*

class AddUserWodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user_wod)

        val picker = findViewById<View>(R.id.datePicker1) as DatePicker
        val date = picker.dayOfMonth.toString() + "/" + (picker.month + 1).toString() + "/" + picker.year
        val intent = Intent(this, UserWodActivity::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_add_wod.setOnClickListener{
            intent.putExtra("my_date", date)

            val myDB = DatabaseHelper(this)
            myDB.add(
                ed_add_name.text.toString().trim { it <= ' ' },
                ed_add_type.text.toString().trim { it <= ' ' },
                date.trim { it <= ' ' }
            )

            startActivity(intent)
        }
    }

    private fun lastUsed(){
        val my_date: String? = intent.getStringExtra("my_date")
        val intent = Intent(this, UserWodActivity::class.java)
        intent.putExtra("my_date", my_date)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        lastUsed()
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
        lastUsed()
        return true
    }
}