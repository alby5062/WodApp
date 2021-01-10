package it.alberto.wodapp.Wod.UserWod

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.add_user_wod.*

class AddUserWodActivity : AppCompatActivity() {

    private var parentLinearLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user_wod)
        parentLinearLayout = findViewById<View>(R.id.parent_linear_layout) as LinearLayout


        val picker = findViewById<View>(R.id.datePicker) as DatePicker
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

    @SuppressLint("InflateParams")
    fun onAddField(v: View?) {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.field, null)
        // Add the new row before the add field button.
        parentLinearLayout!!.addView(rowView, parentLinearLayout!!.childCount - 1)
    }

    fun onDelete(v: View) {
        parentLinearLayout!!.removeView(v.parent as View)
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
