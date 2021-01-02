package it.alberto.wodapp.Wod.UserWod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import it.alberto.wodapp.R


class UserWodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_wod)

        setupActionBarWithNavController(findNavController(R.id.fragment))


        val date = intent.getStringExtra("date")

        //Toast.makeText(applicationContext,date,Toast.LENGTH_LONG).show()
        //println("AOOOOOOOOOOOOOOOOOOOOOOO$date")

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
        )
    }
}