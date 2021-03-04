package it.alberto.wodapp.Database

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import it.alberto.wodapp.Login.Logout

class FirebaseDatabaseHelper {

    fun saveData(uid: String, name: String, surname: String, age: String, gender: String, email: String, password: String){

        val map = mutableMapOf<String,Any>()
        map["Name"] = name
        map["Surname"] = surname
        map["Age"] = age
        map["Gender"] = gender
        map["Email"] = email
        map["Password"] = password

        FirebaseDatabase.getInstance().reference.child("UserData").child(uid).setValue(map)
    }

    fun deleteData(uid: String){

        val user = Firebase.auth.currentUser
        user?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    FirebaseDatabase.getInstance().reference
                        .child("UserData").child(uid).removeValue()
                    Logout().logout()
                    Log.d(TAG, "User account deleted.")
                }
            }
    }
}