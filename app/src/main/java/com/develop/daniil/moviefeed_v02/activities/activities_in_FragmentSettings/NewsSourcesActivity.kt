package com.develop.daniil.moviefeed_v02.activities.activities_in_FragmentSettings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.develop.daniil.moviefeed_v02.R

class NewsSourcesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_sources)

        val text_toolbar = findViewById<TextView>(R.id.text_toolbar)
        text_toolbar.text = "News Sources"
        text_toolbar.textSize = 25F

        /*
        *колхожу стрелочку "назад"
         */
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean { //кнопка "назад"
        onBackPressed()
        return true
    }

}
