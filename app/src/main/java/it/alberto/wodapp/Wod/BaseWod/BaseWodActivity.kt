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
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.list_base_wod.*


class BaseWodActivity : AppCompatActivity() {

    private var myDB: DatabaseHelper? = null
    lateinit var id: ArrayList<String>
    lateinit var name:ArrayList<String>
    lateinit var type:ArrayList<String>
    lateinit var date:ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_base_wod)

        btn_add.setOnClickListener{
            startActivity(Intent(this, AddBaseWodActivity::class.java))
        }

        myDB = DatabaseHelper(this)
        id = ArrayList()
        name = ArrayList()
        type = ArrayList()
        date = ArrayList()

        storeDataInArrays()

        var customAdapter = BaseCustomAdapter(this, this, id, name, type, date)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
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
                }
                empty_image_view.visibility = View.GONE
                no_data.visibility = View.GONE
            }
        }
    }
}