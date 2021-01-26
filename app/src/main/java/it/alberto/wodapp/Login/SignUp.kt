package it.alberto.wodapp.Login

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import it.alberto.wodapp.Database.FirebaseDatabaseHelper
import it.alberto.wodapp.InputCheck
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUp : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var name: String
    private lateinit var surname: String
    private lateinit var age: String
    private lateinit var gender: String

    private val TAG = "BOBBIIIII FIRMINEIIIII"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        title = "Register"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sign_up_button_done.setOnClickListener{
            signUp()
            onBackPressed()
        }
    }

    private fun signUp() {
        val gender_radio: Int = radioGroup!!.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(gender_radio)

        name = name_sign_up.text.toString()
        surname = surname_sign_up.text.toString()
        age = age_sign_up.text.toString()
        gender = radioButton.text.toString()
        email = mail_sign_up.text.toString()
        password = password_sign_up.text.toString()

        if (InputCheck().inputSignUp(name, surname, email, password)) {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //startActivity(Intent(this, Login::class.java))
                        //sendEmailVerification()
                        val uid: String = FirebaseAuth.getInstance().uid.toString()
                        FirebaseDatabaseHelper().saveData(uid, name, surname, age, gender, email, password)
                    }
                }
        }else{
            Toast.makeText(applicationContext, "Insert data", Toast.LENGTH_SHORT).show()
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

    override fun onBackPressed() {
        super.onBackPressed()
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