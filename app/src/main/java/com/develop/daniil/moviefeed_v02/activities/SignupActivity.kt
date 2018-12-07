package com.develop.daniil.moviefeed_v02.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.RequestsClasses.Server
import com.develop.daniil.moviefeed_v02.utils.Crypto
import com.develop.daniil.moviefeed_v02.utils.DBHelper
import com.develop.daniil.moviefeed_v02.utils.funk
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SignupActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acivity_signup)

        var funk: funk = funk()

        var DBHelper: DBHelper = DBHelper(this)

        val skipRegistration_button = findViewById<Button>(R.id.skipRegistration_button)
        skipRegistration_button.setOnClickListener {//переход в маин
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val server = Server(this)

        val loginInput = findViewById<EditText>(R.id.login_editText_SignupActivity)
        val emailInput = findViewById<EditText>(R.id.email_editText_SignupActivity)
        val passInput = findViewById<EditText>(R.id.password_editText_SignupActivity)
        val confPassInput = findViewById<EditText>(R.id.ConfirmPassword_editText)



        val registerButton = findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener {
            if (TextUtils.isEmpty(loginInput.text)) {
                loginInput.setError("Login is required!");
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(emailInput.text)) {
                emailInput.setError("Email is required!");
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(passInput.text)) {
                passInput.setError("Password is required!");
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(confPassInput.text)) {
                confPassInput.setError("Please, input the confirmation of password");
                return@setOnClickListener
            }
            val test = passInput.text.toString().equals(confPassInput.text.toString())
            if(passInput.text.toString().equals(confPassInput.text.toString())){
                //Send registration request
                doAsync {
                    try {
                        val regResp = server.register(encLogin(loginInput.text.toString(),funk.getKluch()),
                            encPass(passInput.text.toString(), funk.getKluch()), encEmail(emailInput.text.toString(), funk.getKluch()))
                        if (regResp.toString().trim().toInt() == 0) {
                            uiThread {
                                val successReg = Toast.makeText(applicationContext, "Success Registration", Toast.LENGTH_SHORT)
                                successReg.show()
                                intent = Intent(this@SignupActivity, MainActivity::class.java)
                                startActivity(intent)
                                }
                        } else if (regResp.toString().trim().toInt() == 1) {
                            loginInput.setError("Login already exists");
                            return@doAsync
                        }
                    }catch (e: Exception) {
                        Log.e("Debug:", e.toString())
                    }
                }
            }


        }
    }

    //encrypt login
    fun encLogin(login: String,key: String): String{
        return Crypto.encryptString(login,Crypto.stringToKey(key))
    }
    //encrypt password
    fun encPass(pass: String,key: String): String{
        return Crypto.encryptString(pass,Crypto.stringToKey(key))
    }
    //encrypt email
    fun encEmail(email: String,key: String): String{
        return Crypto.encryptString(email,Crypto.stringToKey(key))
    }


}