package com.raven.home.domain.mapper

import com.raven.home.data.remote.response.HomeResponse
import com.raven.home.data.remote.response.NetworkResult
import com.raven.home.domain.DomainResult
import com.raven.home.presentation.NewsData
import javax.inject.Inject

class GetNewsMapper @Inject constructor() {

    fun transformDomainToUI(params:NetworkResult<HomeResponse>) : DomainResult<NewsData> {
      return  when(params){
            is NetworkResult.Error -> DomainResult.Error(params.code,params.message)
            is NetworkResult.Exception -> DomainResult.Error(message = params.e.localizedMessage)
            is NetworkResult.Success -> DomainResult.Success(
                NewsData(articles = params.data.results.map {
                    NewsData.Article(
                        it.title,
                        it.abstract
                    )
                })
            )
        }
    }
}
