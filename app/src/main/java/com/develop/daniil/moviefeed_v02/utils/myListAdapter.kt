package com.develop.daniil.moviefeed_v02.utils

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.activities.MainActivity
import kotlinx.android.synthetic.main.progress_bar.view.*
import android.support.v4.content.ContextCompat.startActivity
import android.webkit.WebSettings
import android.webkit.WebView
import com.develop.daniil.moviefeed_v02.activities.WebView as WV
class ListAdapter(private val context: Context,private var itemList: ArrayList<Item> = ArrayList()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val REGULAR_ITEM = 0
    val FOOTER_ITEM = 1




    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

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
                holder.image.getSettings().setLoadWithOverviewMode(true);
                holder.image.getSettings().setUseWideViewPort(true);
                holder.image.getSettings().setJavaScriptEnabled(true);
                holder.image.setInitialScale(1);
                holder.image.setScrollBarStyle(android.webkit.WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                holder.image.setScrollbarFadingEnabled(false);
                holder.image.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                holder.image.loadUrl(itemList[position].image)
                holder.source.text = itemList[position].source
                holder.time.text = itemList[position].time
                holder.link = itemList[position].link
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
            itemList.add(Item("Footer","","","", "",1))
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
        var image: WebView
        var source: TextView
        var time: TextView
        var link:String = ""


        init {
            itemView.setOnClickListener(this)
            name = itemView.findViewById<View>(R.id.newsName_textView) as TextView
            image = itemView.findViewById<View>(R.id.webViewtest) as WebView
            source = itemView.findViewById<View>(R.id.link_textView) as TextView
            time = itemView.findViewById<View>(R.id.newsTime_textView) as TextView
        }

        override fun onClick(view: View) {
            Log.d("onclick", "onClick " + layoutPosition + " " + name.text) //открываем встроенный браузер

            try {
                var intent = Intent(context,WV::class.java)
                intent.putExtra("link",link )
                context.startActivity(intent)
            }catch (e: Exception) {
                Log.e("Debug:", e.toString())
            }



        }
    }

    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar = itemView.progressbar
    }
}