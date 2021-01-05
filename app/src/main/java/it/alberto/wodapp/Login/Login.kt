package it.alberto.wodapp.Login

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils.*
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.activity_login.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class Login : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    var callbackManager : CallbackManager? = null


    lateinit var email: String
    lateinit var password: String

    @SuppressLint("PackageManagerGetSignatures")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = "Login"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        try {
            val info = packageManager.getPackageInfo(
                    "it.alberto.wodapp",
                    PackageManager.GET_SIGNATURES)

            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        }
        catch (e: PackageManager.NameNotFoundException) {}
        catch (e: NoSuchAlgorithmException) {}

        //val user = auth.currentUser

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this,gso)

        auth = FirebaseAuth.getInstance()

        callbackManager = CallbackManager.Factory.create()

        // GOOGLE LOGIN BUTTON
        sign_in_button.setOnClickListener { signIn() }

        // FACEBOOK LOGIN BUTTON
        facebook_login_button.setOnClickListener { facebookLogin() }

        // EMAIL LOGIN BUTTON
        login_button.setOnClickListener { loginEmail() }

        // SIGN UP BUTTON
        sign_up_button.setOnClickListener{ createEmailId() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account= task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e:ApiException){}
        }
    }

    // LOGIN GOOGLE
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential= GoogleAuthProvider.getCredential(idToken,null)

        auth.signInWithCredential(credential).addOnCompleteListener(this){task ->
            if (task.isSuccessful){
                val user = auth.currentUser
                val intent= Intent(this, Logout::class.java)
                startActivity(intent)
            }
        }
    }


    // LOGIN EMAIL PASSWORD
    private fun createEmailId(){
        startActivity(Intent(this, SignUp::class.java))
    }

    private fun loginEmail(){

        email = login_email.text.toString()
        password = login_password.text.toString()

        if (inputCheck(email, password)){

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        moveNextPage()
                        println("Success")

                    } else {
                        Toast.makeText(
                                applicationContext,
                                "Failed. Wrong email - password",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }else{
            Toast.makeText(applicationContext, "Insert email-password", Toast.LENGTH_LONG).show()
        }
    }


    // FACEBOOK LOGIN
    private fun facebookLogin(){
        LoginManager.getInstance().loginBehavior = LoginBehavior.WEB_VIEW_ONLY
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                firebaseAuthWithFacebook(result)
            }

            override fun onCancel() {}

            override fun onError(error: FacebookException?) {}
        })
    }

    fun firebaseAuthWithFacebook(result: LoginResult?){
        val credential = FacebookAuthProvider.getCredential(result?.accessToken?.token!!)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful){
                moveNextPage()
            }
        }
    }

    private fun inputCheck(email: String, password: String): Boolean{
        return !(isEmpty((email)) && isEmpty(password))
    }

    private fun moveNextPage(){
        var currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null){
            startActivity(Intent(this, Logout::class.java))
        }
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        private const val RC_SIGN_IN = 120
    }
}