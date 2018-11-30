package com.develop.daniil.moviefeed_v02.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.RequestsClasses.News
import com.develop.daniil.moviefeed_v02.RequestsClasses.Server
import com.develop.daniil.moviefeed_v02.utils.ListAdapter
import com.develop.daniil.moviefeed_v02.utils.row_model

class FragmentNews: Fragment() {

    private var arrRowModel: ArrayList<row_model> = ArrayList()

    private var view1: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        newsData()
        view1 = view

        setUpRecyclerView(view)
        return view
    }

    private fun newsData() { //заполним листвью
        arrRowModel.add(row_model("Title .......", R.drawable.image, "Kinoposk", "6 hours later"))
        arrRowModel.add(row_model("Title .......", R.drawable.image, "Kinoposk", "6 hours later"))
        arrRowModel.add(row_model("Title .......", R.drawable.image, "Kinoposk", "6 hours later"))
        arrRowModel.add(row_model("Title .......", R.drawable.image, "Kinoposk", "6 hours later"))
        arrRowModel.add(row_model("Title .......", R.drawable.image, "Kinoposk", "6 hours later"))
        arrRowModel.add(row_model("Title .......", R.drawable.image, "Kinoposk", "6 hours later"))
        arrRowModel.add(row_model("Title .......", R.drawable.image, "Kinoposk", "6 hours later"))
    }

    private fun setUpRecyclerView(view: View) { //прикручиваем массив mList адптером к ресайклер вью
        val mRecyclerView = view.findViewById<RecyclerView>(R.id.main_recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
        mRecyclerView.adapter = ListAdapter(view.context, arrRowModel) //adapter в папку utils
    }

    fun update(server: Server) {
        var newsArray: Array<News>? = null
            try {
                newsArray = server.updateNews(2)
            }
            catch (e: Exception) {
                Log.e("Debug", e.toString())
            }

        for (news in newsArray!!) {
            arrRowModel.add(row_model(news.text, R.drawable.image, news.source, news.date))
        }

        val mRecyclerView = view1?.findViewById<RecyclerView>(R.id.main_recyclerView)
        mRecyclerView!!.adapter = ListAdapter(view1!!.context, arrRowModel) //adapter в папку utils
    }

}