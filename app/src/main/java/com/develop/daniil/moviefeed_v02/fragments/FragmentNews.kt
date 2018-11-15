package com.develop.daniil.moviefeed_v02.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.develop.daniil.moviefeed_v02.R
import java.util.*

class FragmentNews: Fragment() {

    private val mList = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        fakeData()
        setUpRecyclerView(view)
        return view
    }

    private fun fakeData() { //заполним листвью
        for (i in 0..20)
            mList.add("Will Smith в очередной раз получил Оскара в Каннах")
    }

    private fun setUpRecyclerView(view: View) { //прикручиваем массив mList адптером к ресайклер вью
        val mRecyclerView = view.findViewById<RecyclerView>(R.id.main_recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
        mRecyclerView.adapter = ListAdapter(mList)
    }

    internal class ListAdapter(list: List<String>?) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
        private val mList = ArrayList<String>()

        init {
            mList.addAll(list!!) //лист не будет Null, проверка на null
        }

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            var textView: TextView = v.findViewById(R.id.NewsName) as TextView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_model, parent, false) //надуваем row_model данными
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) { //классная фича для ресайклер вью,
            holder.textView.text = mList[position] //забиваем держатель textView текстом. Обеспечивает плавность прокрутки
        }

        override fun getItemCount(): Int {
            return mList.size
        }
    }// prosto adapter

}