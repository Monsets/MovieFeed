package com.develop.daniil.moviefeed_v02.RequestsClasses

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.squareup.moshi.Moshi
import khttp.get

class Server(val context: Context) {
    private val serverUrl = "http://ec2-35-159-33-122.eu-central-1.compute.amazonaws.com"
    //Paths for requests
    private val updateNews = "updateNews"
    private val register = "register"
    private val authorization = "authorization"

    //JSON parser's builder
    private val moshi = Moshi.Builder().build()


    fun  updateNews(id: Int, quantity: Int = 15): Array<News>? {
        //URL building
        val url = Uri.parse(serverUrl)
            .buildUpon().path(updateNews).appendQueryParameter("id", id.toString())
            .appendQueryParameter("quantity", quantity.toString()).build().toString()

        //Building JSON parser for array of news
        val jsonAdapter = moshi.adapter<Array<News>>(Array<News>::class.java)
        //Get request
        val res = get(url).text

        val newsArray = jsonAdapter.fromJson(res)

        return newsArray
    }

    fun register(login: String, password: String, email: String): String? {
        //URL building
        val url = Uri.parse(serverUrl)
            .buildUpon().path(register).appendQueryParameter("login", login)
            .appendQueryParameter("pass", password)
            .appendQueryParameter("email", email)
            .appendQueryParameter("data", login.toString()+password.toString()).build().toString()

        val resp: String? = get(url).text

        return resp
    }

    fun authorize(login: String, password: String): String? {
        //URL building
        val url = Uri.parse(serverUrl)
            .buildUpon().path(authorization).appendQueryParameter("login", login.toString())
            .appendQueryParameter("pass", password.toString())
            .appendQueryParameter("data", login.toString()+password.toString()).build().toString()

        val resp: String? = get(url).text

        return resp
    }

    fun getSources(): Array<String>? {
        //TODO: Реализовать получение списка источников с сервера
        var k: Array<String>? = null
        return k
    }
}
