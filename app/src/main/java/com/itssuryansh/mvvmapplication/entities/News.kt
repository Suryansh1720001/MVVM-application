package com.itssuryansh.mvvmapplication.entities

data class News(
    val status : String,
    val totalResults : Int,
    val articles : List<Article>
)
