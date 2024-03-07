package com.raven.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title: String,
    val resume: String,
    val byline: String,
    val publishedDate: String,
    val thumbnailUrl: String,
)