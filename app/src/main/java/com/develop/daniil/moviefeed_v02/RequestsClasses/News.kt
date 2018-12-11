package com.develop.daniil.moviefeed_v02.RequestsClasses

import android.graphics.Bitmap

data class News(
    val id: Int,
    val title: String,
    val text: String,
    val picture: String,
    val source_id: Int,
    val ref: String,
    val date: String,
    var pictureImg: Bitmap
)