package com.develop.daniil.moviefeed_v02.utils

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.develop.daniil.moviefeed_v02.R
import kotlinx.android.synthetic.main.progress_bar.view.*

class ListAdapter(private var itemList: ArrayList<Item> = ArrayList()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val REGULAR_ITEM = 0
    val FOOTER_ITEM = 1
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
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = itemList[position]
        if (item.type == REGULAR_ITEM) {
            return REGULAR_ITEM
        } else if (item.type == FOOTER_ITEM) {
            return FOOTER_ITEM
        }
        throw Exception("Error, unknown view type")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == REGULAR_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_model, parent, false)
            return RegularViewHolder(view)
        } else if (viewType == FOOTER_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.progress_bar, parent, false)
            return FooterViewHolder(view)
        } else {
            throw RuntimeException("The type has to be ONE or TWO")
        }
    }

    // load data in each row element
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            REGULAR_ITEM -> {
                holder as RegularViewHolder
                holder.name.text = itemList[position].name
                holder.image.setImageResource(itemList[position].image)
                holder.link.text = itemList[position].link
                holder.time.text = itemList[position].time
            }
            FOOTER_ITEM -> {
                // no data need to be assigned
            }
            else -> {
                // no data need to be assigned
            }
        }
    }

    // this is required to be called right before loading more items
    fun addFooter() {
        if (!isLoading()) {
            itemList.add(Item("Footer",0,"","", 1))
            notifyItemInserted(itemList.size - 1)
        }
    }

    // this is required to be called right after finish loading the items
    fun removeFooter() {
        if (isLoading()) {
            itemList.removeAt(itemList.size - 1)
            notifyItemRemoved(itemList.size - 1)
        }
    }

    // it is loading if the last item is footer
    fun isLoading() : Boolean {
        return itemList.last().type == FOOTER_ITEM
    }

    fun addItems(items : ArrayList<Item>) {
        val lastPos = itemList.size - 1
        itemList.addAll(items)
        notifyItemRangeInserted(lastPos, items.size)
    }

    inner class RegularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var name: TextView
        var image: ImageView
        var link: TextView
        var time: TextView

        init {
            itemView.setOnClickListener(this)
            name = itemView.findViewById<View>(R.id.newsName_textView) as TextView
            image = itemView.findViewById<View>(R.id.newsPicture_imageView) as ImageView
            link = itemView.findViewById<View>(R.id.link_textView) as TextView
            time = itemView.findViewById<View>(R.id.newsTime_textView) as TextView
        }

        override fun onClick(view: View) {
            Log.d("onclick", "onClick " + layoutPosition + " " + name.text) //открываем встроенный браузер
        }
    }

    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar = itemView.progressbar
    }
}