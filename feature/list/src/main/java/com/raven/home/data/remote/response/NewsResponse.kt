package com.raven.home.data.remote.response

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class NewsResponse (
    val status : String,
    @SerializedName("num_results")
    val nResults : Int,
    val results : List<Article>,
){
    data class Article(
        val title : String,
        val abstract : String,
        val id : BigInteger,
        val source:String,
        @SerializedName("published_date")
        val publishedDate : String,
        val update : String,
        val section :String,
        val subsection :String,
        val byline :String,
        @SerializedName("nytdsection")
        val nytdSection :String,
        @SerializedName("adx_keywords")
        val adxKeywords :String,
        val media : List<Media>

    )

    data class Media(
        val type : String,
        val subtype : String,
        val caption : String,
        val copyright : String,
        @SerializedName("approved_for_syndication")
        val approvedForSyndication : String,
        @SerializedName("media-metadata")
        val metadata : List<Metadata>
    )

    data class Metadata(
        val url : String,
        val format : String,
        val height : Int,
        val width : Int,
    )

}
