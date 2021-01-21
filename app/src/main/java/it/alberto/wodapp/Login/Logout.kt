package it.alberto.wodapp.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import it.alberto.wodapp.MainActivity
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.activity_logout.*


class Logout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        logout_button.setOnClickListener {
            logout()
        }
        home_button.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun logout() {

        // EMAIL LOGOUT
        FirebaseAuth.getInstance().signOut()

        finish()

        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
}