package it.alberto.wodapp.Wod.BaseWod

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.MainActivity
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.UserWod.AddUserWodActivity
import it.alberto.wodapp.Wod.UserWod.CalendarActivity
import kotlinx.android.synthetic.main.list_base_wod.*


class BaseWodActivity : AppCompatActivity() {

    private var myDB: DatabaseHelper? = null
    private lateinit var id: ArrayList<String>
    private lateinit var name:ArrayList<String>
    private lateinit var type:ArrayList<String>
    private lateinit var date:ArrayList<String>
    private lateinit var exercises: ArrayList<String>
    private lateinit var result: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_base_wod)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_add.setOnClickListener{
            finish()
            startActivity(Intent(this, AddBaseToUser::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }

        myDB = DatabaseHelper(this)
        id = ArrayList()
        name = ArrayList()
        type = ArrayList()
        date = ArrayList()
        exercises = ArrayList()
        result = ArrayList()

        storeDataInArrays()

        var customAdapter = BaseCustomAdapter(this, this, id, name, type, date, exercises, result)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
        if (requestCode == 2){
            recreate()
        }
    }

    private fun storeDataInArrays() {
        val cursor: Cursor? = myDB!!.readBaseData()
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
                    result.add(cursor.getString(5))
                }
                empty_image_view.visibility = View.GONE
                no_data.visibility = View.GONE
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        startActivity(Intent(Intent(this, MainActivity::class.java)))
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