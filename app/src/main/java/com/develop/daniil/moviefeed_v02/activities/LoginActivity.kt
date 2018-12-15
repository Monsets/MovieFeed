package com.develop.daniil.moviefeed_v02.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.RequestsClasses.Server
import com.develop.daniil.moviefeed_v02.utils.Crypto
import com.develop.daniil.moviefeed_v02.utils.DBHelper
import com.develop.daniil.moviefeed_v02.utils.funk

class LoginActivity : AppCompatActivity() {
    //lateinit var DBHelper : DBHelper
    private val worker: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.login_button_LoginActivity)
        val login: EditText = findViewById(R.id.login_editText_LoginActivity)
        val pass: EditText = findViewById(R.id.password_editText_LoginActivity)
        val closeButton: Button = findViewById(R.id.closeButton)

        val server = Server(this)
        var DBHelper: DBHelper = DBHelper(this)
        var funk: funk = funk()

       val signup_button: Button = findViewById(R.id.SignUp_Button_LoginActivity)

        signup_button.setOnClickListener {
            //переход на регистрацию
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        closeButton.setOnClickListener{
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val encPass: String = Crypto.getHash(pass.text.toString()) //Получаем хеш пароля
            val encLogin: String =
                Crypto.encryptString(login.text.toString(), Crypto.stringToKey(funk.getKluch()))//Шифруем логин

            //DBHelper.addRecToUserTable("petuch","123456","email@email.com",1)

            // DBHelper.readAll()

            /*this@LoginActivity.runOnUiThread {
                if (server.authorize(encLogin, encPass) == "true") {
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    //TODO: Реализовать обработку ошибок

                    print("huy")
                }
            }*/

            Thread(Runnable {
                Thread.sleep(1000)
                println("test")
            })

            //TODO: Изменить агрументы запроса(если требуется, я хз прост)



        }


    }
}