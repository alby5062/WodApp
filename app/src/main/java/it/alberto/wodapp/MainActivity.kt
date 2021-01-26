package it.alberto.wodapp

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.Login.InfoActivity
import it.alberto.wodapp.Login.Login
import it.alberto.wodapp.Login.Logout
import it.alberto.wodapp.Utility.StopWatch.StopWatch
import it.alberto.wodapp.Utility.Timer.TimerActivity
import it.alberto.wodapp.Wod.BaseWod.BaseWodActivity
import it.alberto.wodapp.Wod.UserWod.CalendarActivity
import it.alberto.wodapp.Wod.UserWod.DetailLastHome
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.last_update_card.*
import kotlinx.android.synthetic.main.utility_card.*

class MainActivity : AppCompatActivity() {

    private var myDB: DatabaseHelper? = null
    private lateinit var id: String
    private lateinit var name: String
    private lateinit var type:String
    private lateinit var date:String
    private lateinit var result: String
    private var userLogged: FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        /*findViewById<FloatingActionButton>(R.id.add).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        myDB = DatabaseHelper(this)
        id = String()
        name = String()
        type = String()
        date = String()
        result = String()

        takeLastUpload()

        last_name_insert.text = name
        last_type_insert.text = type
        last_date_insert.text = date
        last_result_insert.text = result

        userLogged = FirebaseAuth.getInstance().currentUser

        if (userLogged != null){
            include_stop_watch.visibility = View.VISIBLE
            include_timer.visibility = View.VISIBLE
        } else {
            include_stop_watch.visibility = View.GONE
            include_timer.visibility = View.GONE
        }

        include_stop_watch.setOnClickListener {
            finish()
            startActivity(Intent(this, StopWatch::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }

        include_timer.setOnClickListener {
            finish()
            startActivity(Intent(this, TimerActivity::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }

        val add: FloatingActionButton = findViewById(R.id.add)
        add.setOnClickListener {
            finish()
            this.startActivity(Intent(this, BaseWodActivity::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }

        val history: FloatingActionButton = findViewById(R.id.history_button)
        history.setOnClickListener {
            finish()
            this.startActivity(Intent(this, CalendarActivity::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }

        include_last.setOnClickListener {
            finish()
            this.startActivity(Intent(this, DetailLastHome::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    private fun takeLastUpload(){
        val cursor: Cursor? = myDB!!.takeLastUpload()
        if (cursor != null) {
            if (cursor.count == 0){
                include_empty.visibility = View.VISIBLE
                include_last.visibility = View.GONE
            } else {
                while (cursor.moveToNext()){
                    name = (cursor.getString(1))
                    type = (cursor.getString(2))
                    date = (cursor.getString(3))
                    result = (cursor.getString(5))

                }
                include_empty.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // [START check_current_user]
        userLogged = FirebaseAuth.getInstance().currentUser
        if (userLogged != null){
            menuInflater.inflate(R.menu.menu_logged, menu)
        } else {
            menuInflater.inflate(R.menu.menu_main, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {

        R.id.action_logged -> {
            // User chose the "Settings" item, show the app settings UI...
            finish()
            this.startActivity(Intent(this, InfoActivity::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            true
        }

        R.id.action_login -> {
            // User chose the "Settings" item, show the app settings UI...
            finish()
            this.startActivity(Intent(this, Login::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            true
        }

        R.id.action_logout_menu -> {
            Logout().logout()
            finish()
            startActivity(intent)
            overridePendingTransition(
                R.anim.fade_in,
                R.anim.fade_out
            )
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("AOOOOOOOOOOOOOOOOOOOOOOOOOOO")
        finish()
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}