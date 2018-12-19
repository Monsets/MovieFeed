package com.develop.daniil.moviefeed_v02.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.utils.Item
import com.develop.daniil.moviefeed_v02.RequestsClasses.News
import com.develop.daniil.moviefeed_v02.RequestsClasses.Server
import com.develop.daniil.moviefeed_v02.utils.ListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import com.develop.daniil.moviefeed_v02.R.id.view
import com.develop.daniil.moviefeed_v02.activities.LoginActivity
import com.develop.daniil.moviefeed_v02.activities.MainActivity
import com.develop.daniil.moviefeed_v02.activities.WebView


class FragmentNews : Fragment() {

    private val linearLayoutManager = LinearLayoutManager(activity)
    val itemList = ArrayList<Item>()

    private var view1: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val recyclerview = view.findViewById<RecyclerView>(R.id.main_recyclerView)
        val UpSwipe = view.findViewById<SwipeRefreshLayout>(R.id.UpSwipe)
        newsData()
        view1 = view

        setUpRecyclerView(recyclerview, UpSwipe)

        return view
    }



    private fun newsData() {
        // initial list items
        for (i in 0..15) {
            itemList.add(Item("Title", "", "Link", "Time"))
        }
    }

    private fun setUpRecyclerView(recyclerview: RecyclerView, UpSwipe: SwipeRefreshLayout) {
        val itemArrayAdapter = ListAdapter(getActivity()!!.getApplicationContext(), itemList)
        recyclerview.setLayoutManager(linearLayoutManager)
        recyclerview.setItemAnimator(DefaultItemAnimator())
        recyclerview.setAdapter(itemArrayAdapter)


        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // only load more items if it's currently not loading
                if (!itemArrayAdapter.isLoading()) {
                    // only load more items if the last visible item on the screen is the last item
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() >= linearLayoutManager.itemCount - 1) {

                        // add progress bar, the loading footer
                        recyclerview.post {
                            itemArrayAdapter.addFooter()
                        }

                        // load more items after 2 seconds, and remove the loading footer
                        val handler = Handler()
                        handler.postDelayed({
                            getNews(itemArrayAdapter)

                            UpSwipe.isRefreshing = false
                        }, 1000)
                    }
                }
            }
        })

        UpSwipe.setOnRefreshListener {
            val handler = Handler()

            handler.postDelayed({
                getNews(itemArrayAdapter)

                UpSwipe.isRefreshing = false
            }, 2000)
        }
    }

    private fun getNews(itemArrayAdapter:ListAdapter){
        val server = Server(view!!.context)
        doAsync {
            //Get last news from server
            var newsArray: Array<News>? = null
            try {
                newsArray = server.getMoreNews(30)
            } catch (e: Exception) {
                Log.e("Debug", e.toString())
            }

            rebuildNewsList(newsArray!!)
            uiThread {
                itemArrayAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun rebuildNewsList(newsArray: Array<News>) {
        for (i in newsArray.size - 1 downTo 0 step 1) {
            val news = newsArray[i]
            itemList.add(0, Item(news.title, news.picture, news.link, news.date,news.source))
            itemList.remove(itemList.last())
        }
    }
//
//    fun update(server: Server) {
//        doAsync {
//            //Get last news from server
//            var newsArray: Array<News>? = null
//            try {
//                newsArray = server.updateNews(2)
//            } catch (e: Exception) {
//                Log.e("Debug", e.toString())
//            }
//
//            rebuildNewsList(newsArray!!)
//        }


    //Show new news list
//            uiThread {
//                setUpRecyclerView(view1!!)
//            }
//        }
//        //TODO: Записывать новые новости в бд
//    }


}

