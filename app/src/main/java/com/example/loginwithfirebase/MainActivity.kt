package com.example.loginwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginwithfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        try {
            user = FirebaseAuth.getInstance().currentUser!!
            if (user.uid.isNotEmpty()) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }catch (e:Exception){

        }
        binding.register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        binding.login.setOnClickListener {

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

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }else {

            }
        }.addOnFailureListener{

        }
    }
}
