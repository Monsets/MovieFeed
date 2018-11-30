package com.develop.daniil.moviefeed_v02.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.develop.daniil.moviefeed_v02.R

internal class ListAdapter(var context: Context, var arrRowModel: ArrayList<row_model>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var Name: TextView
        var Image: ImageView
        var Link: TextView
        var Time: TextView

        init {
            this.Name = v.findViewById(R.id.newsName_textView) as TextView
            this.Image = v.findViewById(R.id.newsPicture_imageView) as ImageView
            this.Link = v.findViewById(R.id.link_textView) as TextView
            this.Time = v.findViewById(R.id.newsTime_textView) as TextView
        }
    }

    //TODO: Реализовать кликабельные объекты

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_model, parent, false) //надуваем row_model данными
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Name.text = arrRowModel[position].Name
        holder.Image.setImageResource(arrRowModel[position].Image)
        holder.Link.text = arrRowModel[position].Link
        holder.Time.text = arrRowModel[position].Time
    }

    override fun getItemCount(): Int {
        return arrRowModel.size
    }
}// prosto adapter