package com.develop.daniil.moviefeed_v02.fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.activities.LoginActivity






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

        exit_button = view.findViewById<ImageButton>(R.id.exit_button) //buttons
        changeUsername_button = view.findViewById<ImageButton>(R.id.changeUsername_button)
        changeEmail_button = view.findViewById<ImageButton>(R.id.changeEmail_button)
        changePassword_button = view.findViewById<ImageButton>(R.id.changePassword_button)

        newUsername_editText = view.findViewById<EditText>(R.id.newUsername_editText) //editTexts
        newEmail_editText = view.findViewById<EditText>(R.id.newEmail_editText)
        currentPassword_editText = view.findViewById<EditText>(R.id.currentPassword_editText)
        newPassword_editText = view.findViewById<EditText>(R.id.newPassword_editText)

        workWithEditTexts(R.id.newUsername_editText, view) //вынес в отдельную функцию работу с эдитами (для удобства)
        workWithEditTexts(R.id.newEmail_editText, view)

        changeUsername_button!!.setOnClickListener {
            if(username_isClicked == 0) { //т.е. текст уже введён в эдит, но клика по кнопке ещё не было

                username_isClicked = 1
                changeUsername_button!!.setImageResource(R.drawable.ic_check) //значок по дефолту
                showAlert(R.id.changeUsername_button) //показываю тост, в нём методы ОнКлик

            }
        }
        changeEmail_button!!.setOnClickListener {//просто тост
            if(email_isClicked == 0) {

                email_isClicked = 1
                changeEmail_button!!.setImageResource(R.drawable.ic_check) //значок по дефолту
                showAlert(R.id.changeEmail_button) //показываю тост, в нём методы ОнКлик
            }
        }
        changePassword_button!!.setOnClickListener {//просто тост
            val toast = Toast.makeText(activity, "new password saved", Toast.LENGTH_SHORT)
            toast.show()
        }
        exit_button!!.setOnClickListener {//через конпку выхода переходим на  ЛОГИН (можно удалить)
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

    private fun showAlert(id: Int){
        val mBuilder = AlertDialog.Builder(context!!)
        mBuilder.setTitle("Do you want to save changes?")
            .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                when(id){
                    R.id.changeUsername_button ->{
                        val toast = Toast.makeText(activity, "new username saved", Toast.LENGTH_SHORT) //просто тост
                        newUsername_editText!!.text.clear()
                        toast.show()
                    }
                    R.id.changeEmail_button ->{
                        val toast1 = Toast.makeText(activity, "new E-mail saved", Toast.LENGTH_SHORT)
                        newEmail_editText!!.text.clear()
                        toast1.show()
                    }
                }
            }
            .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                when(id){
                    R.id.changeUsername_button ->{
                        newUsername_editText!!.text.clear()
                    }
                    R.id.changeEmail_button ->{
                        newEmail_editText!!.text.clear()
                    }
                }
                // User cancelled the dialog
            }
        // Create the AlertDialog object and return it
        mBuilder.create().show()
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