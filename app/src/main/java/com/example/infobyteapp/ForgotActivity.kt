package com.example.infobyteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.infobyteapp.databinding.ActivityForgotBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private val binding:ActivityForgotBinding by lazy {
        ActivityForgotBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()
        binding.btnReset.setOnClickListener {
            val email=binding.forgotEmail.text.toString()
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if(it.isSuccessful)
                {
                    Toast.makeText(applicationContext,"Check email",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                }
                else
                {
                    Toast.makeText(applicationContext,it.exception?.message,Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}