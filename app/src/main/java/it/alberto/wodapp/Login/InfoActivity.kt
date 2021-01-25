package it.alberto.wodapp.Login

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.Database.FirebaseDatabaseHelper
import it.alberto.wodapp.MainActivity
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.UserWod.UserWodActivity
import kotlinx.android.synthetic.main.info_activity.*
import kotlinx.android.synthetic.main.info_card.*
import kotlinx.android.synthetic.main.info_card_google.*

class InfoActivity : AppCompatActivity() {

    private lateinit var providerId: String
    private lateinit var uid: String
    private var nameG: String? = null
    private var emailG: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_activity)

        title = "Information"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                // Id of the provider (ex: google.com)
                providerId = profile.providerId
                // Name and email address
                nameG = profile.displayName
                emailG = profile.email
            }
        }

        if (providerId == "google.com"){
            include_info.visibility = View.GONE
            name_txv_insert_google.text = nameG
            mail_txv_insert_google.text = emailG
        } else {
            include_info_google.visibility = View.GONE
            val uid: String = FirebaseAuth.getInstance().uid.toString()
            readSingleData(uid)
        }

        logout_button_card.setOnClickListener {
            Logout().logout()
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        btn_delete.setOnClickListener {
            confirmDialog(uid)
        }

        btn_delete_google.setOnClickListener {
            confirmDialogGoogle()
        }

        logout_button_card_google.setOnClickListener {
            Logout().logout()
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun readSingleData(uid: String) {

        FirebaseDatabase.getInstance().reference.child("UserData").child(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}

                override fun onDataChange(p0: DataSnapshot) {
                    val map = p0.value as Map<*, *>
                    name_txv_insert.text = map["Name"].toString()
                    surname_txv_insert.text = map["Surname"].toString()
                    age_txv_insert.text = map["Age"].toString()
                    gender_txv_insert.text = map["Gender"].toString()
                    mail_txv_insert.text = map["Email"].toString()
                }
            })
    }

    private fun confirmDialog(uid: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete all information?")
        builder.setPositiveButton("Yes"
        ) { _, _ ->
            val myDB = DatabaseHelper(this)
            myDB.deleteAllData()
            FirebaseDatabaseHelper().deleteData(uid)
            //Refresh Activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No"
        ) { _, _ -> }
        builder.create().show()
    }

    private fun confirmDialogGoogle(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete all information?")
        builder.setPositiveButton("Yes"
        ) { _, _ ->
            val myDB = DatabaseHelper(this)
            myDB.deleteAllData()

            val user_google = Firebase.auth.currentUser
            user_google?.delete()
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Logout().logout()
                        Log.d(ContentValues.TAG, "User account deleted.")
                    }
                }
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        builder.setNegativeButton("No"
        ) { _, _ -> }
        builder.create().show()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}