package com.example.myfirebaseapplication

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirebaseapplication.Utility.Constant
import com.example.myfirebaseapplication.Utility.showToast
import com.example.myfirebaseapplication.databinding.ActivityLogInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
private var progessBar:Dialog?=null

    private val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 9001

    // Adding Google sign-in client
    var mGoogleSignInClient: GoogleSignInClient? = null

    //Creating member variable for FirebaseAuth
    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        binding.dummyButton.setOnClickListener {
            logIn()

        }
        currentUserNotEmpty()

        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("958769471097-bbhberld8p4q4mabt3l2oltrs5lsm2s6.apps.googleusercontent.com")
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()

        binding.signInButton.setOnClickListener { view: View? ->
          //  Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
            signInGoogle()
        }
        binding.tvForgotPassword.setOnClickListener { view: View? ->
            showRecoverPasswordDialog()
        }

        binding.tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
        }

    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // onActivityResult() function : this is where
    // we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    // this is where we update the UI after Google signin takes place
    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth!!.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val user: FirebaseUser = mAuth!!.getCurrentUser()!!
                Log.d(TAG, "signInWithCredential:success: currentUser: " + user.email)
                showToast("Firebase Authentication Succeeded ")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }



    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            startActivity(
                Intent(
                    this, MainActivity
                    ::class.java
                )
            )
            finish()
        }
    }
  private  fun logIn(){
        val email=binding.editText.text.toString()
        val password=binding.Password.text.toString()
        mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                showToast("LogIn Successfully")
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                showToast("LogIn Failed")
            }
        }
    }
    private fun currentUserNotEmpty(){
        val auth = Firebase.auth
        val currentUser=auth.currentUser
        if (currentUser!=null){
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra(Constant.USER_NAME,binding.editText.text.toString())
            startActivity(intent)
        }
    }

    private fun showRecoverPasswordDialog() {


        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Recover Password")
        val linearLayout = LinearLayout(this)
        val emailet = EditText(this)

        // write the email using which you registered
        emailet.setText("Email")
        emailet.minEms = 16
        emailet.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        linearLayout.addView(emailet)
        linearLayout.setPadding(10, 10, 10, 10)
        builder.setView(linearLayout)

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Send",
            DialogInterface.OnClickListener { dialog, which ->
                val email = emailet.text.toString().trim { it <= ' ' }
                beginRecovery(email)
            })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        builder.create().show()
    }
    private fun beginRecovery(email: String) {
        progessBar = Dialog(this)

        progessBar!!.setCanceledOnTouchOutside(false)
        progessBar!!.show()

        // calling sendPasswordResetEmail
        // open your email and write the new
        // password and then you can login
        mAuth!!.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            progessBar!!.dismiss()
            if (task.isSuccessful) {
                // if isSuccessful then done message will be shown
                // and you can change the password
                Toast.makeText(this, "Done sent", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            progessBar!!.dismiss()
            Toast.makeText(this, "Error Failed", Toast.LENGTH_LONG).show()
        }
    }
}

