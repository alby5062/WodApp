package it.alberto.wodapp.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    private val TAG = "BOBBIIIII FIRMINEIIIII"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        title = "Register"

        sign_up_button_done.setOnClickListener{
            signUp()
        }
    }

    private fun signUp() {
        var name = name_sign_up.text.toString()
        var surname = surname_sign_up.text.toString()
        val age = age_sign_up.text.toString()
        var gender = spinner
        var email = mail_sign_up.text.toString()
        var password = password_sign_up.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d(TAG, "Success")
                startActivity(Intent(this, Login::class.java))
                sendEmailVerification()
            }
        }
    }

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = Firebase.auth.currentUser

        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                    Toast.makeText(applicationContext, "Confirm your account. Email sent.", Toast.LENGTH_SHORT).show()

                }
            }
        // [END send_email_verification]
    }
}