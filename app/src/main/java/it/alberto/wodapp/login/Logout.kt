package it.alberto.wodapp.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import it.alberto.wodapp.MainActivity
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.activity_logout.*


class Logout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        logout_button.setOnClickListener {
            logout()
        }
        home_button.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun logout() {

        // EMAIL LOGOUT
        FirebaseAuth.getInstance().signOut()

        // FACEBOOK LOGIN SESSION STOP
        LoginManager.getInstance().logOut()

        finish()

        startActivity(Intent(this, MainActivity::class.java))

    }
}