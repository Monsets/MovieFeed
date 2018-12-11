package com.develop.daniil.moviefeed_v02.activities

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.develop.daniil.moviefeed_v02.R
import com.develop.daniil.moviefeed_v02.RequestsClasses.Server
import com.develop.daniil.moviefeed_v02.fragments.*
import com.develop.daniil.moviefeed_v02.utils.DBHelper
import kotlinx.android.synthetic.main.activity_web_view.*


class WebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView.loadUrl("https://www.tutorialkart.com/kotlin-android/android-webview-example/")

    }

}
