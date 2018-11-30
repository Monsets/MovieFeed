package com.develop.daniil.moviefeed_v02.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.R.id.login_button_LoginActivity
import com.develop.daniil.moviefeed_v02.RequestsClasses.Server
import com.develop.daniil.moviefeed_v02.utils.Crypto
import kotlinx.android.synthetic.main.activity_login.*
import javax.crypto.SecretKey

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val server = Server(this)

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

            val keysec: SecretKey = Crypto.stringToKey("mEg8brQLbDGkSMIqZt7TteXo1RGcrIMntlXItcSDZIk=")
            val seckey: String = Crypto.keyToString(keysec)

            //TODO: Изменить агрументы запроса(если требуется, я хз прост)
            if (server.authorize(encLogin, encPass) == "true") {
            //TODO: Реализовать успешный вход
            }
            else {
            //TODO: Реализовать обработку ошибок
            }

        }


    }
}