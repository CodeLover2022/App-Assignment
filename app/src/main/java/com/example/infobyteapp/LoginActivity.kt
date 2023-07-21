package com.example.infobyteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.infobyteapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
 private val binding:ActivityLoginBinding by lazy {
     ActivityLoginBinding.inflate(layoutInflater)
 }
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()
       binding.tvForgotPass.setOnClickListener {
           startActivity(Intent(this,ForgotActivity::class.java))
           finish()
       }
       binding.notReg.setOnClickListener {
           startActivity(Intent(this,SignUpActivity::class.java))
           finish()
       }
        binding.btnLogin.setOnClickListener {
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            if(email.isEmpty() || password.isEmpty())
            {
                Toast.makeText(applicationContext,"Please fill the field",Toast.LENGTH_LONG).show()
            }
            else
            {
                UserLogin(email,password)
            }
        }
    }
    private fun UserLogin(email:String,password:String)
    {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful)
            {
                Toast.makeText(applicationContext,"User Signed in",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(applicationContext,it.exception?.message,Toast.LENGTH_LONG).show()

            }
        }
    }
}