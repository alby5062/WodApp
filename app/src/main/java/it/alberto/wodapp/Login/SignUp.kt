package it.alberto.wodapp.Login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.RadioButton
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sign_up_button_done.setOnClickListener{
            signUp()
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

        if (inputCheck(name, surname, email, password)) {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Success")
                        Log.d(TAG, gender)
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


/*val gender = resources.getStringArray(R.array.gender_spinner)
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
    }*/