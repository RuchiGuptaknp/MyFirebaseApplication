package com.example.myfirebaseapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myfirebaseapplication.Utility.showToast
import com.example.myfirebaseapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignUpBinding
        .inflate(layoutInflater)
    }
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mAuth=FirebaseAuth.getInstance()

      binding.btnSignIn.setOnClickListener {
            signUpUser()
        }
       binding.tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signUpUser() {
        val email = binding.editText.text.toString()
        val pass = binding.Password.text.toString()
        val confirmPassword = binding.ConfirmPassword.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            try {
                if (it.isSuccessful) {

                    Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                  showToast(it.exception.toString())
                }
            }catch (e:Exception){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        }
    }
}