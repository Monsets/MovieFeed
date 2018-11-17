package com.develop.daniil.moviefeed_v02.activities.activities_in_FragmentSettings

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.develop.daniil.moviefeed_v02.R


class LanguageActivity : AppCompatActivity() {

    var english_button: Button? = null
    var russian_button: Button? = null
    var ukrainian_button: Button? = null
    var german_button: Button? = null
    var english_check: ImageView? = null
    var russian_check: ImageView? = null
    var ukrainian_check: ImageView? = null
    var german_check: ImageView? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        val text_toolbar = findViewById<TextView>(R.id.text_toolbar)
        text_toolbar.text = "Choose your language"
        text_toolbar.textSize = 20F

        english_button = findViewById(R.id.english_button)
        russian_button = findViewById(R.id.russian_button)
        ukrainian_button = findViewById(R.id.ukrainian_button)
        german_button = findViewById(R.id.german_button)

        english_check = findViewById(R.id.english_check)
        russian_check = findViewById(R.id.russian_check)
        ukrainian_check = findViewById(R.id.ukrainian_check)
        german_check = findViewById(R.id.german_check)

        english_check!!.visibility = View.VISIBLE //default lang

        english_button!!.setOnClickListener {
            setChecked(english_check!!)
        }
        russian_button!!.setOnClickListener {
            setChecked(russian_check!!)
        }
        ukrainian_button!!.setOnClickListener {
            setChecked(ukrainian_check!!)
        }
        german_button!!.setOnClickListener {
            setChecked(german_check!!)
        }
        /*
        *колхожу стрелочку "назад"
         */
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    fun setChecked(temp: ImageView){
        english_check!!.visibility = View.INVISIBLE
        russian_check!!.visibility = View.INVISIBLE
        ukrainian_check!!.visibility = View.INVISIBLE
        german_check!!.visibility = View.INVISIBLE

        temp.visibility = View.VISIBLE
        Toast.makeText(this,"Language saved", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean { //кнопка "назад"
        onBackPressed()
        return true
    }
}
