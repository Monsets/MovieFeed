package com.develop.daniil.moviefeed_v02.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.utils.Crypto

class SignupActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acivity_signup)

        val skipRegistration_button = findViewById<Button>(R.id.skipRegistration_button)
        skipRegistration_button.setOnClickListener {//переход в маин
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val loginInput = findViewById<EditText>(R.id.login_editText_SignupActivity)
        val emailInput = findViewById<EditText>(R.id.email_editText_SignupActivity)
        val passInput = findViewById<EditText>(R.id.password_editText_SignupActivity)
        val confPassInput = findViewById<EditText>(R.id.ConfirmPassword_editText)

        val registerButton = findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener {

            //Get key from DB

            val url: String = "http://35.159.33.122/register"

        }
    }

    fun encLogin(login: String,key: String): String{
        return Crypto.encryptString(login,Crypto.stringToKey(key))
    }

    fun encPass(pass: String,key: String): String{
        return Crypto.encryptString(pass,Crypto.stringToKey(key))
    }

    fun encEmail(email: String,key: String): String{
        return Crypto.encryptString(email,Crypto.stringToKey(key))
    }

    fun encConfPass(confPass: String,key: String): String{
        return Crypto.encryptString(confPass,Crypto.stringToKey(key))
    }

}