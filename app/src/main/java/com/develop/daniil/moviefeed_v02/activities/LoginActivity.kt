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

        val loginButton: Button = findViewById(R.id.login_button_LoginActivity)
        val login: EditText = findViewById(R.id.login_editText_LoginActivity)
        val pass: EditText = findViewById(R.id.password_editText_LoginActivity)

        val server = Server(this)
        var DBHelper: DBHelper = DBHelper(this)
        var funk: funk = funk()

       val signup_button: Button = findViewById(R.id.SignUp_Button_LoginActivity)


        /*if(DBHelper.getUserInfo() == 0){
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }*/

        signup_button.setOnClickListener {
            //переход на регистрацию
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish();
        }

        loginButton.setOnClickListener {
            if (TextUtils.isEmpty(login.text)) {
                login.setError("Login is required!");
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(pass.text)) {
                pass.setError("Password is required!");
                return@setOnClickListener
            }
            val encPass: String =
                Crypto.encryptString(pass.text.toString(), Crypto.stringToKey(funk.getKluch()))//Шифруем пароль
            val encLogin: String =
                Crypto.encryptString(login.text.toString(), Crypto.stringToKey(funk.getKluch()))//Шифруем логин

            //DBHelper.addRecToUserTable("petuch","123456","email@email.com",1)

            //DBHelper.readAll()

            //Отправка и обработка запроса авторизации
            doAsync {
                val auth = server.authorize(encLogin, encPass)
                try {
                    uiThread {
                        if (auth.toString().trim().toInt() == 0) {
                            DBHelper.addRecToUserTable(encLogin,encPass,"none",0)
                            intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish();
                        } else {
                            val toast = Toast.makeText(applicationContext, "User is unregistered!", Toast.LENGTH_SHORT)
                            toast.show()
                        }
                    }
                } catch (e: Exception) {
                    Log.e("Debug:", e.toString())
                }
            }
        }
    }
}