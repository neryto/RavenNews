package com.raven.home.presentation

data class NewsData(
    val articles : List<Article>
){
    data class Article(
        val title : String,
        val abstract : String,
    )
}
