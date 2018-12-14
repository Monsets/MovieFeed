package com.develop.daniil.moviefeed_v02.RequestsClasses

data class News(
    var id: Int,
    var title: String,
    var text: String,
    var picture: String,
    var source_id: String,
    var source: String,
    var link: String,
    var date: String
)