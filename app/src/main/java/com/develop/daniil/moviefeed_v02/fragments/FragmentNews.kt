package com.develop.daniil.moviefeed_v02.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.utils.ListAdapter
import com.develop.daniil.moviefeed_v02.utils.row_model

class FragmentNews: Fragment() {

    private var arrRowModel: ArrayList<row_model> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        newsData()
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
}