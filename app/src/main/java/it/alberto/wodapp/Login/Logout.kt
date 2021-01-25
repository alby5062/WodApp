package it.alberto.wodapp.Login

import com.google.firebase.auth.FirebaseAuth

class Logout{

    fun logout() {
        // EMAIL LOGOUT
        FirebaseAuth.getInstance().signOut()
    }
}