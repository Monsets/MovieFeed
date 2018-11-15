package com.develop.daniil.moviefeed_v02.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.TextView
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.fragments.FragmentNews
import com.develop.daniil.moviefeed_v02.fragments.FragmentProfile
import com.develop.daniil.moviefeed_v02.fragments.FragmentReviews
import com.develop.daniil.moviefeed_v02.fragments.FragmentSearch

class MainActivity : AppCompatActivity() {

    var profileButton:ImageButton? = null
    var newsButton:ImageButton? = null
    var reviewsButton:ImageButton? = null
    var searchButton:ImageButton? = null
    var settingsButton:ImageButton? = null
    var textView:TextView? = null
    var fragmentNews: FragmentNews? = null
    var fragmentReviews: FragmentReviews? = null
    var fragmentSearch: FragmentSearch? = null
    var fragmentProfile: FragmentProfile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentNews = FragmentNews()  //создание фрагментов-объектов
        fragmentReviews = FragmentReviews()
        fragmentSearch = FragmentSearch()
        fragmentProfile = FragmentProfile()

        profileButton = findViewById(R.id.Button_Profile)
        newsButton = findViewById(R.id.Button_News)
        reviewsButton = findViewById(R.id.Button_Reviews)
        searchButton = findViewById(R.id.Button_Search)
        settingsButton = findViewById(R.id.Button_Settings)
        textView =  findViewById(R.id.text_toolbar) //загаловок тулбара


        showNewsFragment()  //default fragment

        newsButton!!.setOnClickListener {
            showNewsFragment()
        }
        reviewsButton!!.setOnClickListener {
            showReviewsFragment()
        }
        searchButton!!.setOnClickListener {
            showSearchFragment()
        }
        profileButton!!.setOnClickListener {
            showProfileFragment()
        }
        settingsButton!!.setOnClickListener {
            intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

    }

    fun showNewsFragment() {
        val ft = supportFragmentManager.beginTransaction()
        if (fragmentNews!!.isAdded) {  //проверка на существование фрагмента (чтобы не создавать новый каждый раз..)
            ft.show(fragmentNews)
        } else {
            ft.add(R.id.FrameContainer, fragmentNews, "1")
        }

        if (fragmentReviews!!.isAdded) {  //скрываем ненужные фрагменты
            ft.hide(fragmentReviews)
        }
        if (fragmentSearch!!.isAdded) {
            ft.hide(fragmentSearch)
        }
        if (fragmentProfile!!.isAdded) {
            ft.hide(fragmentProfile)
        }
        textView!!.text = "NEWS"//меняем загаловок тулбара
        ft.commit() //подгружаем фрагмент
    }

    fun showReviewsFragment() {
        val ft = supportFragmentManager.beginTransaction()
        if (fragmentReviews!!.isAdded) {  //проверка на существование фрагмента (чтобы не создавать новый каждый раз..)
            ft.show(fragmentReviews)
        } else {
            ft.add(R.id.FrameContainer, fragmentReviews, "2")
        }

        if (fragmentNews!!.isAdded) { //скрываем ненужные фрагменты
            ft.hide(fragmentNews)
        }
        if (fragmentSearch!!.isAdded) {
            ft.hide(fragmentSearch)
        }
        if (fragmentProfile!!.isAdded) {
            ft.hide(fragmentProfile)
        }
        textView!!.text = "REVIEWS"  //меняем загаловок тулбара
        ft.commit()  //подгружаем фрагмент
    }

    fun showSearchFragment() {
        val ft = supportFragmentManager.beginTransaction()
        if (fragmentSearch!!.isAdded) {  //проверка на существование фрагмента (чтобы не создавать новый каждый раз..)
            ft.show(fragmentSearch)
        } else {
            ft.add(R.id.FrameContainer, fragmentSearch, "3")
        }

        if (fragmentNews!!.isAdded) {  //скрываем ненужные фрагменты
            ft.hide(fragmentNews)
        }
        if (fragmentReviews!!.isAdded) {
            ft.hide(fragmentReviews)
        }
        if (fragmentProfile!!.isAdded) {
            ft.hide(fragmentProfile)
        }
        textView!!.text = "Search"  //меняем загаловок тулбара
        ft.commit()  //подгружаем фрагмент
    }

    fun showProfileFragment() {
        val ft = supportFragmentManager.beginTransaction()
        if (fragmentProfile!!.isAdded) {  //проверка на существование фрагмента (чтобы не создавать новый каждый раз..)
            ft.show(fragmentProfile)
        } else {
            ft.add(R.id.FrameContainer, fragmentProfile, "4")
        }

        if (fragmentNews!!.isAdded) {  //скрываем ненужные фрагменты
            ft.hide(fragmentNews)
        }
        if (fragmentReviews!!.isAdded) {
            ft.hide(fragmentReviews)
        }
        if (fragmentSearch!!.isAdded) {
            ft.hide(fragmentSearch)
        }
        textView!!.text = "Profile"  //меняем загаловок тулбара
        ft.commit()  //подгружаем фрагмент
    }

}
