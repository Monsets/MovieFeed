package com.develop.daniil.moviefeed_v02.activities.activities_in_FragmentSettings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
    var temp: ImageView? = null
    var sPref: SharedPreferences? = null //сохр язык
    var savedId: Int? = R.id.english_check

    val SAVED_TEXT = "saved_text"

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

        try {
            loadLanguage()
        }
        catch (e: Throwable){
            saveLanguage(R.id.english_check)
            loadLanguage()
        }

        english_button!!.setOnClickListener {
            setChecked(R.id.english_check)
            saveLanguage(R.id.english_check)
        }
        russian_button!!.setOnClickListener {
            setChecked(R.id.russian_check)
            saveLanguage(R.id.russian_check)
        }
        ukrainian_button!!.setOnClickListener {
            setChecked(R.id.ukrainian_check)
            saveLanguage(R.id.ukrainian_check)
        }
        german_button!!.setOnClickListener {
            setChecked(R.id.german_check)
            saveLanguage(R.id.german_check)
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

    private fun loadLanguage() {
        sPref = getPreferences(Context.MODE_PRIVATE)
        savedId = sPref!!.getInt(SAVED_TEXT, R.id.english_check)
        setChecked(savedId!!)
    }

    fun setChecked(id: Int){
        english_check!!.visibility = View.INVISIBLE
        russian_check!!.visibility = View.INVISIBLE
        ukrainian_check!!.visibility = View.INVISIBLE
        german_check!!.visibility = View.INVISIBLE

        temp = findViewById(id)
        temp!!.visibility = View.VISIBLE //текущий "чек" делаю видимым
    }

    private fun saveLanguage(id: Int){
        sPref = getPreferences(Context.MODE_PRIVATE) //сохраняю выбранный язык
        val ed = sPref!!.edit()
        ed.putInt(SAVED_TEXT, id)
        ed.apply()
    }

    override fun onDestroy() {  //если пользователь закрыл приложение
        super.onDestroy()
        loadLanguage()
    }

    override fun onSupportNavigateUp(): Boolean { //кнопка "назад"
        onBackPressed()
        return true
    }
}
