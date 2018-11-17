package com.develop.daniil.moviefeed_v02.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.develop.daniil.moviefeed_v02.R
import java.util.*

internal class ListAdapter(list: List<String>?) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private val mList = ArrayList<String>()

    init {
        mList.addAll(list!!) //лист не будет Null, проверка на null
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textView: TextView = v.findViewById(R.id.newsName_textView) as TextView
        var imageView: ImageView = v.findViewById(R.id.newsPicture_imageView) as ImageView
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