
package com.example.ataylarproject.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ataylarproject.R
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.LoginPostModel
import com.example.ataylarproject.Models.User
import com.example.com.iottech.evedijitalandroid.LoginService


class LoginActivity : AppCompatActivity() {
    lateinit var editTextPhone: EditText
    lateinit var editTextPassword: EditText
    private var loginPostModel: LoginPostModel? = null
    val MIN_PASSWORD_LENGTH = 6
    lateinit var loginBT: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewInitializations()
        setTextChangeListeners()
        setInitialData()

    }

    fun viewInitializations() {
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextPassword = findViewById(R.id.editTextPassword)
        loginBT = findViewById(R.id.loginBT)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)




        loginBT.setOnClickListener {
            postLogin()
        }


    }

    private fun setTextChangeListeners() {

        editTextPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int,
            ) {
             loginPostModel?.phoneNumber = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int,
            ) {
                loginPostModel?.password = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


    }

    private fun setInitialData() {
        loginPostModel = LoginPostModel("", "")
    }

    private fun postLogin() {
        if (loginPostModel != null) {
            LoginService.postLogin(loginPostModel!!,
                { success ->
                    Constants.isUserLoggedIn = true
                    val data = success as User
                    Constants.ADMIN_ID = data.adminId.toString()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }, { failure ->
                    // startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    print(failure)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
            )

        }
    }
}


