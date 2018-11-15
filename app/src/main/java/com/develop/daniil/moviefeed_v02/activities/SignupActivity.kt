package com.develop.daniil.moviefeed_v02.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.develop.daniil.moviefeed_v02.R

class SignupActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acivity_signup)

        val login_button = findViewById<Button>(R.id.LogIn_button_SignupActivity)
        login_button.setOnClickListener {//переход на логин
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}