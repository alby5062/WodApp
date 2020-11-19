package it.alberto.wodapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {

    private var auth : FirebaseAuth? = null
    var callbackManager : CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = "Login"

        auth = FirebaseAuth.getInstance()

        callbackManager = CallbackManager.Factory.create()

        // FACEBOOK LOGIN BUTTON
        facebook_login_button.setOnClickListener {
            facebookLogin()
        }
        // EMAIL LOGIN BUTTON
        login_button.setOnClickListener {
            loginEmail()
        }
        // SIGN UP BUTTON
        sign_up_button.setOnClickListener{
            createEmailId()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    // LOGIN EMAIL PASSWORD
    private fun createEmailId(){
        startActivity(Intent(this, SignUp::class.java))
    }
    private fun loginEmail(){
        var email = login_email.text.toString()
        var password = login_password.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                moveNextPage()
                println("Success")
            } else {
                Toast.makeText(applicationContext, "Failed. Wrong email - password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // LOGIN FACEBOOK
    private fun facebookLogin(){
        LoginManager.getInstance().loginBehavior = LoginBehavior.WEB_VIEW_ONLY
        LoginManager.getInstance().logInWithReadPermissions(
            this, listOf(
                "public_profile",
                "email"
            )
        )
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    firebaseAuthWithFacebook(result)
                }
                override fun onCancel() {}
                override fun onError(error: FacebookException?) {}
            })
    }
    fun firebaseAuthWithFacebook(result: LoginResult?){
        var credential = FacebookAuthProvider.getCredential(result?.accessToken?.token!!)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful){
                println("Success")
                moveNextPage()
            }
        }
    }

    private fun moveNextPage(){
        var currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null){
            startActivity(Intent(this, Logout::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        );
    }
}