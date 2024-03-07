package com.raven.home.domain.mapper

import com.raven.database.entity.NewsEntity
import com.raven.home.data.remote.response.NewsResponse
import com.raven.home.data.remote.response.NetworkResult
import com.raven.home.domain.DomainResult
import com.raven.home.domain.NewsData
import javax.inject.Inject

class GetNewsMapper @Inject constructor() {

    fun transformNetworkToUI(params: NetworkResult<NewsResponse>): DomainResult<NewsData> {
        return when (params) {
            is NetworkResult.Error -> DomainResult.Error(params.code, params.message)
            is NetworkResult.Exception -> DomainResult.Error(message = params.e.localizedMessage)
            is NetworkResult.Success -> DomainResult.Success(
                NewsData(articles = params.data.results.map { result ->
                    val mediaImage = getMediaImage(result.media)
                    val metadata = getMetadata(mediaImage)
                    NewsData.Article(
                        result.id.toString(),
                        result.title,
                        result.abstract,
                        result.byline,
                        result.publishedDate,
                        metadata?.url
                    )
                })
            )
        }
    }

    fun transformNetworkToDb(data: List<NewsResponse.Article>): List<NewsEntity> =
        data.map { news ->
            val mediaImage = getMediaImage(news.media)
            val metadata = getMetadata(mediaImage)
            NewsEntity(
                id = news.id.toString(),
                title = news.title,
                resume = news.abstract,
                byline = news.byline,
                publishedDate = news.publishedDate,
                thumbnailUrl = metadata?.url ?: ""
            )
        }

    fun transformDbToUI(newsEntity: List<NewsEntity>): DomainResult<NewsData> {
       val articles =  newsEntity.map { news->
            NewsData.Article(
                news.id,
                news.title,
                news.resume,
                news.byline,
                news.publishedDate,
                news.thumbnailUrl
            )
        }
       return DomainResult.Success(
           NewsData(
               articles
           )
       )

    }


    private fun getMediaImage(media: List<NewsResponse.Media>): NewsResponse.Media? =
        media.firstOrNull { mediaFiltered ->
            mediaFiltered.type == "image"
        }

    private fun getMetadata(media: NewsResponse.Media?): NewsResponse.Metadata? =
        media?.metadata?.firstOrNull { metadaFiltered ->
            metadaFiltered.format == "Standard Thumbnail"
        }


}
