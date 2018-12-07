package com.develop.daniil.moviefeed_v02.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.R.id.login_button_LoginActivity
import com.develop.daniil.moviefeed_v02.RequestsClasses.Server
import com.develop.daniil.moviefeed_v02.utils.Crypto
import com.develop.daniil.moviefeed_v02.utils.DBHelper
import com.develop.daniil.moviefeed_v02.utils.funk
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.security.AccessController.getContext
import javax.crypto.SecretKey

class LoginActivity : AppCompatActivity() {
    //lateinit var DBHelper : DBHelper
    private val worker: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val server = Server(this)
        var DBHelper: DBHelper = DBHelper(this)
        var funk: funk = funk()

        val loginButton: Button = findViewById(R.id.login_button_LoginActivity)
        val login: EditText = findViewById(R.id.login_editText_LoginActivity)
        val pass: EditText = findViewById(R.id.password_editText_LoginActivity)

        val signup_button: Button = findViewById(R.id.SignUp_Button_LoginActivity)

        signup_button.setOnClickListener {
            //переход на регистрацию
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val encPass: String = Crypto.encryptString(pass.text.toString(), Crypto.stringToKey(funk.getKluch()))//Шифруем логин//Получаем хеш пароля
            val encLogin: String =
                Crypto.encryptString(login.text.toString(), Crypto.stringToKey(funk.getKluch()))//Шифруем логин
            intent = Intent(this, MainActivity::class.java)
            DBHelper.addRecToUserTable("petuch","123456","email@email.com",1)

            DBHelper.readAll()
            doAsync {
                val test = server.authorize(encLogin, encPass)
                try {
                    uiThread {
                        if (test!!.toInt() == 0) {
                            startActivity(intent)

                        } else {

                        }
                    }
                } catch (e: Exception) {
                    Log.e("Debug:", e.toString())
                }
            }
        }
            //TODO: Изменить агрументы запроса(если требуется, я хз прост)


    }
}