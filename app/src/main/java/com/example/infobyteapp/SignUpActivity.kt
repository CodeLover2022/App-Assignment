package com.example.infobyteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.infobyteapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private val binding:ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()
        if(auth.currentUser!=null)
        {
            startActivity(Intent(this,MainActivity::class.java))
        }
        binding.btnRegister.setOnClickListener {
            val email_reg=binding.regEmail.text.toString()
            val pass=binding.regPass.text.toString()
            val confirmPass=binding.confirmPass.text.toString()
            if(email_reg.isEmpty() || pass.isEmpty() || confirmPass.isEmpty())
            { Toast.makeText(applicationContext,"Please fill the field",Toast.LENGTH_SHORT).show()}
            else if(pass!=confirmPass)
            {
                Toast.makeText(applicationContext,"Password is not correct",Toast.LENGTH_SHORT).show()
            }
            else
            {
                createUser(email_reg,pass)
            }
        }
        binding.loginPage.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun createUser(email: String, pass: String) {
             auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                 if(it.isSuccessful)
                 {
                     Toast.makeText(applicationContext,"User created",Toast.LENGTH_SHORT).show()
                     startActivity(Intent(this,LoginActivity::class.java))
                     finish()
                 }
                 else
                 {
                     Toast.makeText(applicationContext,it.exception?.message,Toast.LENGTH_SHORT).show()
                 }
             }
    }
}