package it.alberto.wodapp.Login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    lateinit var email: String
    lateinit var password: String
    lateinit var name: String
    lateinit var surname: String
    private lateinit var age: String
    lateinit var gender: String

    private val TAG = "BOBBIIIII FIRMINEIIIII"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        title = "Register"

        val gender = resources.getStringArray(R.array.gender_spinner)
        val spinner = findViewById<Spinner>(R.id.spinner)

        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, gender)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(applicationContext,getString(R.string.selected_item) + " " + "" + gender[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        sign_up_button_done.setOnClickListener{
            signUp()
        }
    }

    private fun signUp() {
        name = name_sign_up.text.toString()
        surname = surname_sign_up.text.toString()
        age = age_sign_up.text.toString()
        gender = spinner.selectedItem.toString()
        email = mail_sign_up.text.toString()
        password = password_sign_up.text.toString()

        if (inputCheck(name, surname, email, password)) {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Success")
                        startActivity(Intent(this, Login::class.java))
                        sendEmailVerification()
                        FirebaseDatabase.getInstance().reference.child(name).child("UserData").child("Name").setValue(name)
                        FirebaseDatabase.getInstance().reference.child(name).child("UserData").child("Surname").setValue(surname)
                        FirebaseDatabase.getInstance().reference.child(name).child("UserData").child("Gender").setValue(gender)
                        FirebaseDatabase.getInstance().reference.child(name).child("UserData").child("Age").setValue(age)
                        FirebaseDatabase.getInstance().reference.child(name).child("UserData").child("Email").setValue(email)
                        FirebaseDatabase.getInstance().reference.child(name).child("UserData").child("Password").setValue(password)
                    }
                }
        }else{
            Toast.makeText(applicationContext, "Insert data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, surname: String, email: String, password: String): Boolean{
        return !(TextUtils.isEmpty((name)) &&
                TextUtils.isEmpty((surname)) &&
                TextUtils.isEmpty((email)) &&
                TextUtils.isEmpty(password))
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


/*val spinner: Spinner = findViewById(R.id.spinner)
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter.createFromResource(
                this,
                R.array.gender_spinner,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }





            */