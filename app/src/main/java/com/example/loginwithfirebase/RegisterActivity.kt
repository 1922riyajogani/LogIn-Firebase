package com.example.loginwithfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginwithfirebase.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.gotoLogin.setOnClickListener {
            finish()
        }
        binding.register.setOnClickListener {

            var email = binding.email.text.toString()
            var password = binding.password.text.toString()

            if(email.isNotEmpty()){
                if(email.contains("@") && email.contains(".")){
                    register(email, password)
                }else{
                    Toast.makeText(this, "Enter Valid Email!!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please enter email!!!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun register(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful) {
                finish()
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Login Failed "+it.message, Toast.LENGTH_SHORT).show()
        }

    }


}