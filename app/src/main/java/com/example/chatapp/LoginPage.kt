package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginPage : AppCompatActivity() {
    private lateinit var edt_Email: EditText
    private lateinit var edt_Password: EditText
    private lateinit var logInBtn: Button
    private lateinit var signUpBtn: Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        edt_Email = findViewById(R.id.edt_email)
        edt_Password = findViewById(R.id.edt_password)
        logInBtn = findViewById(R.id.logInBtn)
        signUpBtn = findViewById(R.id.signUpBtn)

        signUpBtn.setOnClickListener{
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
        }

        logInBtn.setOnClickListener {
            val intent = Intent(this,LoginPage::class.java)
            val email = edt_Email.text.toString()
            val password = edt_Password.text.toString()
            login(email,password)
            startActivity(intent)
        }


    }


    private fun login (email: String, password: String){
        //Logic for logging in user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@LoginPage, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }
                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"User could not be authenticated",Toast.LENGTH_SHORT).show()

                }
            }
    }
}