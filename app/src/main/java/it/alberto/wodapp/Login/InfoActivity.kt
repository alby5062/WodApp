package it.alberto.wodapp.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.MainActivity
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.info_activity.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_activity)

        title = "Information"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        logout_button_card.setOnClickListener {
            startActivity(Intent(this, Logout::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}