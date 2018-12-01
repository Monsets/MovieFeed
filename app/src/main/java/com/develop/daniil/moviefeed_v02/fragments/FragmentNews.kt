package com.develop.daniil.moviefeed_v02.fragments

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.utils.Item
import com.develop.daniil.moviefeed_v02.utils.ListAdapter

class FragmentNews: Fragment() {

    private val linearLayoutManager = LinearLayoutManager(activity)
    val itemList = ArrayList<Item>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val recyclerview = view.findViewById<RecyclerView>(R.id.main_recyclerView)

        newsData()
        setUpRecyclerView(recyclerview)
        return view
    }

    private fun newsData() {
        // initial list items
        for (i in 0..20) {
            itemList.add(Item("Title", R.drawable.image, "Link","Time"))
        }
    }

        private fun setUpRecyclerView(recyclerview: RecyclerView){
            val itemArrayAdapter = ListAdapter(itemList)
            recyclerview.setLayoutManager(linearLayoutManager)
            recyclerview.setItemAnimator(DefaultItemAnimator())
            recyclerview.setAdapter(itemArrayAdapter)

            recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    // only load more items if it's currently not loading
                    if (!itemArrayAdapter.isLoading()) {
                        // only load more items if the last visible item on the screen is the last item
                        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() >= linearLayoutManager.itemCount - 1 ) {

                            // add progress bar, the loading footer
                            recyclerview.post {
                                itemArrayAdapter.addFooter()
                            }

                            // load more items after 2 seconds, and remove the loading footer
                            val handler = Handler()
                            handler.postDelayed({
                                itemArrayAdapter.removeFooter()
                                val newItems = ArrayList<Item>()
                                for (i in itemList.size..itemList.size + 19) {
                                    newItems.add(Item("Title", R.drawable.image, "Link","Time"))
                                }
                                itemArrayAdapter.addItems(newItems)
                            }, 2000)
                        }
                    }
                }
            })
    }

}