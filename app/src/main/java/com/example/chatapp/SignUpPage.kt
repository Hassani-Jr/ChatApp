package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ActionCodeEmailInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpPage : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edt_Email: EditText
    private lateinit var edt_Password: EditText
    private lateinit var signUpBtn: Button
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        supportActionBar?.hide()

        edtName = findViewById(R.id.edt_name)
        edt_Email = findViewById(R.id.edt_email)
        edt_Password = findViewById(R.id.edt_password)
        signUpBtn = findViewById(R.id.signUpBtn)
        mAuth = FirebaseAuth.getInstance()

        signUpBtn.setOnClickListener{
            val name = edtName.text.toString()
            val email = edt_Email.text.toString()
            val password = edt_Password.text.toString()


            signUp(name,email,password)
        }

    }
    private fun signUp(name:String,email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this@SignUpPage, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"Some error occurred",Toast.LENGTH_SHORT).show()
                    // If sign in fails, display a message to the user.

                }
            }
    }
    private fun addUserToDatabase(name: String,email: String, uid : String ){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))

    }
}