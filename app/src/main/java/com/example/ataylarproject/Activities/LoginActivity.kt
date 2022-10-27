
package com.example.ataylarproject.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ataylarproject.R
import android.content.Intent
import android.widget.EditText


class LoginActivity : AppCompatActivity() {
    lateinit var editTextPhone: EditText
    lateinit var editTextPassword: EditText
    val MIN_PASSWORD_LENGTH = 6
    lateinit var loginBT: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewInitializations()
    }

    fun viewInitializations() {
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextPassword = findViewById(R.id.editTextPassword)
        loginBT = findViewById(R.id.loginBT)
        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)




        loginBT.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }}


