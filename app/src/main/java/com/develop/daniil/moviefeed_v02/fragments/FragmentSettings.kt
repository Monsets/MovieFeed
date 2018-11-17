package com.develop.daniil.moviefeed_v02.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.activities.activities_in_FragmentSettings.*

class FragmentSettings: Fragment() {

    var newsSources_button: Button? = null
    var reviewsSources_button: Button? = null
    var language_button: Button? = null
    var aboutApp_button: Button? = null
    var aboutAuthors_button: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        newsSources_button = view.findViewById(R.id.newsSources_button)
        reviewsSources_button = view.findViewById(R.id.reviewsSources_button)
        language_button = view.findViewById(R.id.language_button)
        aboutApp_button = view.findViewById(R.id.aboutApp_button)
        aboutAuthors_button = view.findViewById(R.id.aboutAuthors_button)

        newsSources_button!!.setOnClickListener {//переход к выбору ресурсов
            val intent = Intent(activity, NewsSourcesActivity::class.java)
            startActivity(intent)
        }
        reviewsSources_button!!.setOnClickListener {//переход к выбору ресурсов
            val intent = Intent(activity, ReviewsSourcesActivity::class.java)
            startActivity(intent)
        }
        language_button!!.setOnClickListener {
            val intent = Intent(activity, LanguageActivity::class.java)
            startActivity(intent)
        }
        aboutApp_button!!.setOnClickListener {
            val intent = Intent(activity, AboutAppActivity::class.java)
            startActivity(intent)
        }
        aboutAuthors_button!!.setOnClickListener {
            val intent = Intent(activity, AboutAuthorsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}