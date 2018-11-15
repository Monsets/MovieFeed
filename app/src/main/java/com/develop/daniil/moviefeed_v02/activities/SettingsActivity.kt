package com.develop.daniil.moviefeed_v02.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.develop.daniil.moviefeed_v02.R
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity: AppCompatActivity() {

//    var newsSources_data = arrayOf("Rotten tomatoes", "Moviee", "MyFilm", "RTF", "Kinopoisk")
//    var language_data = arrayOf("English", "Russian", "Spanish")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        /*
        *колхожу стрелочку "назад"
         */
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        aboutAuthors_button!!.setOnClickListener {
            intent = Intent(this, Authors::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean { //кнопка "назад"
        onBackPressed()
        return true
    }
}