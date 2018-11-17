package com.develop.daniil.moviefeed_v02.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.develop.daniil.moviefeed_v02.R

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signup_button: Button = findViewById(R.id.SignUp_Button_LoginActivity)

        signup_button.setOnClickListener {//переход на регистрацию
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}