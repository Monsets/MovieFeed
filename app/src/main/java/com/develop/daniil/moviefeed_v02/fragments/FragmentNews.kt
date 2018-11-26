package com.develop.daniil.moviefeed_v02.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.activities.MainActivity
import com.develop.daniil.moviefeed_v02.utils.ListAdapter
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("ValidFragment")
class FragmentNews @SuppressLint("ValidFragment") constructor
    (val cont: Context): Fragment() {

    private val mList = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        fakeData()
        setUpRecyclerView(view)
        return view
    }

    private data class newsContainer (
        val NEWS: ArrayList<JSONObject>
    )

    private data class news (
        val id: String,
        val titleNews: String,
        val textNews: String,
        val pictureNews: String,
        val sourceNews: String,
        val refNews: String,
        val dateNews: String
    )

    private val queue = Volley.newRequestQueue(cont)

    private fun fakeData() { //заполним листвью
        for (i in 0..20)
           mList.add("Режиссер «Бамблби» может поставить «Стражей Галактики 3»")
    }

    private fun setUpRecyclerView(view: View) { //прикручиваем массив mList адптером к ресайклер вью
        val mRecyclerView = view.findViewById<RecyclerView>(R.id.main_recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
        mRecyclerView.adapter = ListAdapter(mList) //adapter в папку utils
    }
}