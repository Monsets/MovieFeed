package com.develop.daniil.moviefeed_v02.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.R.id.login_button_LoginActivity
import com.develop.daniil.moviefeed_v02.utils.Crypto
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.login_button_LoginActivity)
        val login: EditText = findViewById(R.id.login_editText_LoginActivity)
        val pass: EditText = findViewById(R.id.password_editText_LoginActivity)

        val signup_button: Button = findViewById(R.id.SignUp_Button_LoginActivity)

        signup_button.setOnClickListener {
            //переход на регистрацию
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener{
            val encPass: String = Crypto.getHash(pass.text.toString()) //Получаем хеш пароля
            val encLogin: String =
                Crypto.encryptString(login.text.toString(), Crypto.stringToKey(encPass))//Шифруем логин
            val decLogin: String = Crypto.decryptString(encLogin, Crypto.stringToKey(encPass))
        }


    }
}