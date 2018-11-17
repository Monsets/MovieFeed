package com.develop.daniil.moviefeed_v02.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.TextView
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.fragments.*

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
    var fragmentSettings: FragmentSettings? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentNews = FragmentNews()  //создание фрагментов-объектов
        fragmentReviews = FragmentReviews()
        fragmentSearch = FragmentSearch()
        fragmentProfile = FragmentProfile()
        fragmentSettings = FragmentSettings()

        profileButton = findViewById(R.id.Button_Profile) //инициализация кнопок
        newsButton = findViewById(R.id.Button_News)
        reviewsButton = findViewById(R.id.Button_Reviews)
        searchButton = findViewById(R.id.Button_Search)
        settingsButton = findViewById(R.id.Button_Settings)
        textView =  findViewById(R.id.text_toolbar) //загаловок тулбара

        showFragment(fragmentNews!!,1,"NEWS") //default fragment
        newsButton!!.setImageResource(R.drawable.ic_news_black) //default button

        newsButton!!.setOnClickListener {
            showFragment(fragmentNews!!,1,"NEWS") //подгрузка текущего фрагмента
            bottomNavigationHelper(newsButton!!, R.drawable.ic_news_black) //выделение кнопки чёрным
        }
        reviewsButton!!.setOnClickListener {
            showFragment(fragmentReviews!!,2,"REVIEWS")
            bottomNavigationHelper(reviewsButton!!, R.drawable.ic_reviews_black)
        }
        searchButton!!.setOnClickListener {
            showFragment(fragmentSearch!!,3,"SEARCH")
            bottomNavigationHelper(searchButton!!, R.drawable.ic_search_black)
        }
        profileButton!!.setOnClickListener {
            showFragment(fragmentProfile!!,4,"PROFILE")
            bottomNavigationHelper(profileButton!!, R.drawable.ic_profile_black)
        }
        settingsButton!!.setOnClickListener {
            showFragment(fragmentSettings!!,5,"SETTINGS")
            bottomNavigationHelper(settingsButton!!, R.drawable.ic_settings_black)
        }

    }

    fun bottomNavigationHelper(button: ImageButton, ic: Int){
        newsButton!!.setImageResource(R.drawable.ic_news_grey) //"красим" все кнопки в серый
        reviewsButton!!.setImageResource(R.drawable.ic_reviews_grey)
        searchButton!!.setImageResource(R.drawable.ic_search_grey)
        profileButton!!.setImageResource(R.drawable.ic_profile_grey)
        settingsButton!!.setImageResource(R.drawable.ic_settings_grey)

        button.setImageResource(ic)  //"красим" нужную кнопку в черный
    }

    fun showFragment(currentFragment: Fragment, num: Int, title: String) {
        val ft = supportFragmentManager.beginTransaction()

        if (fragmentNews!!.isAdded) {  //скрываем все фрагменты, если они уже существуют
            ft.hide(fragmentNews)
        }
        if (fragmentReviews!!.isAdded) {
            ft.hide(fragmentReviews)
        }
        if (fragmentSearch!!.isAdded) {
            ft.hide(fragmentSearch)
        }
        if (fragmentProfile!!.isAdded) {
            ft.hide(fragmentProfile)
        }
        if (fragmentSettings!!.isAdded) {
            ft.hide(fragmentSettings)
        }

        if (currentFragment.isAdded) {  //проверка на существование фрагмента (чтобы не создавать новый каждый раз..)
            ft.show(currentFragment)
        } else {
            ft.add(R.id.FrameContainer, currentFragment, num.toString())
        }

        textView!!.text = title  //меняем загаловок тулбара
        ft.commit() //подгружаем фрагмент
    }

}
