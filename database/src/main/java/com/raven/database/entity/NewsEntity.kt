package com.raven.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey
    val id : String,
    val title: String,
    val resume: String,
    val byline: String,
    val publishedDate: String,
    val thumbnailUrl: String,
)