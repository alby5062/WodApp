package it.alberto.wodapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import it.alberto.wodapp.Login.DashboardActivity
import it.alberto.wodapp.Login.Login
import it.alberto.wodapp.Login.Logout
import it.alberto.wodapp.Wod.BaseWod.BaseWodActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        /*findViewById<FloatingActionButton>(R.id.add).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        val add: FloatingActionButton = findViewById(R.id.add)
        add.setOnClickListener {

            this.startActivity(Intent(this, BaseWodActivity::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }

        val history: FloatingActionButton = findViewById(R.id.history_button)
        history.setOnClickListener {

            this.startActivity(Intent(this, DashboardActivity::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // [START check_current_user]
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null){
            menuInflater.inflate(R.menu.menu_logged, menu)
        } else {
            menuInflater.inflate(R.menu.menu_main, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {

        R.id.action_logged -> {
            // User chose the "Settings" item, show the app settings UI...
            this.startActivity(Intent(this, Logout::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            true
        }

        R.id.action_login -> {
            // User chose the "Settings" item, show the app settings UI...
            this.startActivity(Intent(this, Login::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            true
        }

        R.id.action_logout_menu -> {

            this.startActivity(Intent(this, Logout::class.java))
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}