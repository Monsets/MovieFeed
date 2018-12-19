package com.develop.daniil.moviefeed_v02.RequestsClasses

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.develop.daniil.moviefeed_v02.utils.DBHelper
import com.squareup.moshi.Moshi
import khttp.get
import java.net.URI
import java.net.URL
import java.net.MalformedURLException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class Server(var context: Context) {
    private var serverUrl = "http://ec2-35-159-33-122.eu-central-1.compute.amazonaws.com"
    //Paths for requests
    private var updateNews = "updateNews"
    private var register = "register"
    private var authorization = "authorization"
    private var getSources = "getSources"
    private var getMoreNews = "getMoreNews"

    //JSON parser's builder
    private var moshi = Moshi.Builder().build()

    private var DBHelper = DBHelper(context)


    fun  updateNews(id: Int, quantity: Int = 15): Array<News>? {
        //URL building
        var url = Uri.parse(serverUrl)
            .buildUpon().path(updateNews).appendQueryParameter("id", id.toString())
            .appendQueryParameter("quantity", quantity.toString()).build().toString()

        //Building JSON parser for array of news
        var jsonAdapter = moshi.adapter<Array<News>>(Array<News>::class.java)
        //Get request
        var res = get(url).text

        var newsArray = jsonAdapter.fromJson(res)

        //Set sources
        var sources = getSources()
        var setsources = DBHelper.setSources(sources!!)
        for (news in newsArray.orEmpty()){
            var id = news.source_id.trim()
            news.source = DBHelper.getSource(news.source_id.trim().toInt())

            //Formating date to local time
            try {
               val formatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
                formatter.setTimeZone(TimeZone.getTimeZone("GMT"))
               val date = formatter.parse(news.date)

                val formatterToRussan = SimpleDateFormat("dd MMM yyyy HH:mm:ss")
                val date2 = formatterToRussan.format(date)

                news.date = date2.toString()
            }catch (e: Exception){
                Log.e("Debug:", e.toString())
            }

        }



        return newsArray
    }

    fun register(login: String, password: String, email: String): String? {
        //URL building
        var url = Uri.parse(serverUrl)
            .buildUpon().path(register).appendQueryParameter("login", login)
            .appendQueryParameter("pass", password)
            .appendQueryParameter("email", email)
            .appendQueryParameter("data", login.toString()+password.toString()).build().toString()

        var resp: String? = get(url).text

        return resp
    }

    fun authorize(login: String, password: String): String? {
        //URL building
        var url = Uri.parse(serverUrl)
            .buildUpon().path(authorization).appendQueryParameter("login", login.toString())
            .appendQueryParameter("pass", password.toString())
            .appendQueryParameter("data", login.toString()+password.toString()).build().toString()

        var resp: String? = get(url).text

        return resp
    }

    fun getSources(): Array<Sources>? {
        var url = Uri.parse(serverUrl)
            .buildUpon().path(getSources).toString()
        //Building JSON parser for array of news
        var jsonAdapter = moshi.adapter<Array<Sources>>(Array<Sources>::class.java)
        //Get request
        var rez = get(url).text


        var sourcesArray= jsonAdapter.fromJson(rez)
        return sourcesArray
    }

    fun getMoreNews(id: Int, quantity: Int = 15): Array<News>?{
        //URL building
        var url = Uri.parse(serverUrl)
            .buildUpon().path(getMoreNews).appendQueryParameter("id", id.toString())
            .appendQueryParameter("quantity", quantity.toString()).build().toString()

        //Building JSON parser for array of news
        var jsonAdapter = moshi.adapter<Array<News>>(Array<News>::class.java)
        //Get request
        var res = get(url).text

        var newsArray = jsonAdapter.fromJson(res)

        //Set sources
        var sources = getSources()
        var setsources = DBHelper.setSources(sources!!)
        for (news in newsArray.orEmpty()) {
            var id = news.source_id.trim()
            news.source = DBHelper.getSource(news.source_id.trim().toInt())

            //Formating date to local time
            try {
                val formatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
                formatter.setTimeZone(TimeZone.getTimeZone("GMT"))
                val date = formatter.parse(news.date)

                val formatterToRussan = SimpleDateFormat("dd MMM yyyy HH:mm:ss")
                val date2 = formatterToRussan.format(date)

                news.date = date2.toString()
            } catch (e: Exception) {
                Log.e("Debug:", e.toString())
            }
        }
        return newsArray
    }
}
