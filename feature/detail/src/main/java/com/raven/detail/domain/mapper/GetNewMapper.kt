package com.raven.detail.domain.mapper

import com.raven.database.entity.NewsEntity
import com.raven.detail.domain.NewData
import javax.inject.Inject

class GetNewMapper @Inject constructor() {
    fun transformDBToUI(input : NewsEntity) : NewData =
        NewData(
            input.title,
            input.resume,
            input.byline,
            input.publishedDate
        )
}