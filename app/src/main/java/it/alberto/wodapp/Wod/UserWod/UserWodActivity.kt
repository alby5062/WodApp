package it.alberto.wodapp.Wod.UserWod

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.BaseWod.BaseWodActivity
import kotlinx.android.synthetic.main.list_base_wod.*

class UserWodActivity : AppCompatActivity() {

    private var myDB: DatabaseHelper? = null
    lateinit var id: ArrayList<String>
    lateinit var name:ArrayList<String>
    lateinit var type:ArrayList<String>
    lateinit var date:ArrayList<String>
    lateinit var exercises: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_user_wod)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val my_date: String? = intent.getStringExtra("my_date")

        btn_add.setOnClickListener{
            val intent = Intent(this, AddUserWodActivity::class.java)
            intent.putExtra("my_date",my_date)
            startActivity(intent)
            finish()
        }

        myDB = DatabaseHelper(this)
        id = ArrayList()
        name = ArrayList()
        type = ArrayList()
        date = ArrayList()
        exercises = ArrayList()

        storeDataInArrays(my_date.toString())

        val customAdapter = UserCustomAdapter(this, this, id, name, type, date, exercises)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun storeDataInArrays(my_date: String) {

        val cursor: Cursor? = myDB!!.readUserData(my_date)
        if (cursor != null) {
            if (cursor.count == 0) {
                empty_image_view.visibility = View.VISIBLE
                no_data.visibility = View.VISIBLE
            } else {
                while (cursor.moveToNext()) {
                    id.add(cursor.getString(0))
                    name.add(cursor.getString(1))
                    type.add(cursor.getString(2))
                    date.add(cursor.getString(3))
                    exercises.add(cursor.getString(4))
                }
                empty_image_view.visibility = View.GONE
                no_data.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.delete_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            confirmDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete all Data?")
        builder.setPositiveButton("Yes"
        ) { _, _ ->
            val myDB = DatabaseHelper(this)
            myDB.deleteAllData()
            //Refresh Activity
            val intent = Intent(this, UserWodActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No"
        ) { _, _ -> }
        builder.create().show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, CalendarActivity::class.java))
        finish()
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
        startActivity(Intent(this, CalendarActivity::class.java))
        return true
    }
}