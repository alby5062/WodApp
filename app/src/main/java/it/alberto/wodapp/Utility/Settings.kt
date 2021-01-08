package it.alberto.wodapp.Utility

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.R

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        //setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Settings"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}