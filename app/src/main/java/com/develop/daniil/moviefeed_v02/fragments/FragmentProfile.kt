package com.develop.daniil.moviefeed_v02.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.activities.LoginActivity
import com.develop.daniil.moviefeed_v02.activities.MainActivity
import com.develop.daniil.moviefeed_v02.utils.Crypto
import com.develop.daniil.moviefeed_v02.utils.DBHelper
import com.develop.daniil.moviefeed_v02.utils.funk


class FragmentProfile: Fragment() {

    var exit_button: ImageButton? = null
    var changeUsername_button: ImageButton? = null
    var changeEmail_button: ImageButton? = null
    var changePassword_button: ImageButton? = null

    var newUsername_editText: EditText? = null
    var newEmail_editText: EditText? = null
    var currentPassword_editText: EditText? = null
    var newPassword_editText: EditText? = null
    var username_isClicked: Int? = 1  //по дефолту в нажатом состоянии
    var email_isClicked: Int? = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        var DBHelper: DBHelper = DBHelper(getActivity()!!.getApplicationContext())
        var funk: funk = funk()

        exit_button = view.findViewById<ImageButton>(R.id.exit_button) //buttons
        changeUsername_button = view.findViewById<ImageButton>(R.id.changeUsername_button)
        changeEmail_button = view.findViewById<ImageButton>(R.id.changeEmail_button)
        changePassword_button = view.findViewById<ImageButton>(R.id.changePassword_button)

        newUsername_editText = view.findViewById<EditText>(R.id.newUsername_editText) //editTexts
        newEmail_editText = view.findViewById<EditText>(R.id.newEmail_editText)
        currentPassword_editText = view.findViewById<EditText>(R.id.currentPassword_editText)
        newPassword_editText = view.findViewById<EditText>(R.id.newPassword_editText)

        val curname = view.findViewById<TextView>(R.id.username_textView)
        val curEmail = view.findViewById<TextView>(R.id.Email_textView)

        workWithEditTexts(R.id.newUsername_editText, view) //вынес в отдельную функцию работу с эдитами (для удобства)
        workWithEditTexts(R.id.newEmail_editText, view)

        if(DBHelper.getUserInfo() != 1){
            val info = DBHelper.getUserData()

            curname.text = Crypto.decryptString(info[0],Crypto.stringToKey(funk.getKluch()))
            curEmail.text = info[1]
        }




        changeUsername_button!!.setOnClickListener {
            if(username_isClicked == 0) { //т.е. текст уже введён в эдит, но клика по кнопке ещё не было

                username_isClicked = 1
                changeUsername_button!!.setImageResource(R.drawable.ic_check) //значок по дефолту
                val toast = Toast.makeText(activity, "new username saved", Toast.LENGTH_SHORT) //просто тост
                toast.show()
            }
        }
        changeEmail_button!!.setOnClickListener {//просто тост
            if(email_isClicked == 0) {

                email_isClicked = 1
                changeEmail_button!!.setImageResource(R.drawable.ic_check) //значок по дефолту
                val toast1 = Toast.makeText(activity, "new E-mail saved", Toast.LENGTH_SHORT)
                toast1.show()
            }
        }
        changePassword_button!!.setOnClickListener {//просто тост
            val toast = Toast.makeText(activity, "new password saved", Toast.LENGTH_SHORT)
            toast.show()
        }
        exit_button!!.setOnClickListener {//через конпку выхода переходим на  ЛОГИН (можно удалить)
            try {
                DBHelper.clearTable("tblUser")
                DBHelper.readAll()
            }catch (e: Exception){
                Log.e("Debug:", e.toString())
            }
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        //тщетные попытки добавить кнопку-выхода
//        val mToolbar = view.findViewById(R.id.toolbar) as Toolbar
//        (activity as AppCompatActivity).setSupportActionBar(mToolbar)

//        val Toolbar = view.findViewById<Toolbar>(R.id.toolbar)
//
//        SupportActionBar.SetDisplayHomeAsUpEnabled(true);
//        SupportActionBar.SetHomeButtonEnabled(true);
        return view
    }

    private fun workWithEditTexts(id: Int, view: View){
        val temp = view.findViewById<EditText>(id)
        temp.addTextChangedListener(object : TextWatcher { //при изменении текста

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length != 0){
                    when(id){
                        R.id.newUsername_editText -> {
                            changeUsername_button!!.setImageResource(R.drawable.ic_exmp) //смена значка
                            username_isClicked = 0
                        }

                        R.id.newEmail_editText -> {
                            changeEmail_button!!.setImageResource(R.drawable.ic_exmp) //смена значка
                            email_isClicked = 0
                        }
                    }
                }
                else
                {
                    when(id){
                        R.id.newUsername_editText -> {
                            changeUsername_button!!.setImageResource(R.drawable.ic_check) //смена значка
                            username_isClicked = 1
                        }

                        R.id.newEmail_editText -> {
                            changeEmail_button!!.setImageResource(R.drawable.ic_check) //смена значка
                            email_isClicked = 1
                        }
                    }

                }
            }
        })

    }


//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        if(item!!.itemId == R.id.exit_button){
//            val intent = Intent(activity, MainActivity::class.java)
//            startActivity(intent)
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
}