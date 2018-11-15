package com.develop.daniil.moviefeed_v02.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.activities.LoginActivity




class FragmentProfile: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val exit_button = view.findViewById<ImageButton>(R.id.exit_button)
        val changeUsername_button= view.findViewById<ImageButton>(R.id.changeUsername_button)
        val changeEmail_button= view.findViewById<ImageButton>(R.id.changeEmail_button)
        val changePassword_button= view.findViewById<ImageButton>(R.id.changePassword_button)

        changeUsername_button.setOnClickListener {//просто тост
            val toast = Toast.makeText(activity, "new username saved", Toast.LENGTH_SHORT)
            toast.show()
        }
        changeEmail_button.setOnClickListener {//просто тост
            val toast = Toast.makeText(activity, "new E-mail saved", Toast.LENGTH_SHORT)
            toast.show()
        }
        changePassword_button.setOnClickListener {//просто тост
            val toast = Toast.makeText(activity, "new password saved", Toast.LENGTH_SHORT)
            toast.show()
        }
        exit_button.setOnClickListener {//через конпку выхода переходим на  ЛОГИН (можно удалить)
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



//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        if(item!!.itemId == R.id.exit_button){
//            val intent = Intent(activity, MainActivity::class.java)
//            startActivity(intent)
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
}