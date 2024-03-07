package com.raven.home.domain

data class NewsData(
    val articles: List<Article>
) {
    data class Article(
        val id:String,
        val title: String,
        val abstract: String,
        val byline: String,
        val publishedDate: String,
        val thumbnailUrl: String?,
    )
}
