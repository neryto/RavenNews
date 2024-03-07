package com.raven.home.domain.usecases

import com.raven.database.entity.NewsEntity
import com.raven.home.data.remote.response.NewsResponse
import com.raven.home.data.remote.response.NetworkResult
import com.raven.home.domain.DomainResult
import com.raven.home.domain.NewsDataSource
import com.raven.database.NewsLocalDataSource
import com.raven.home.domain.mapper.GetNewsMapper
import com.raven.home.domain.NewsData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GeNewsUseCaseTest {

    @InjectMockKs
    private lateinit var useCase: GeNewsUseCase

    @MockK
    private lateinit var dataSource: NewsDataSource

    @MockK
    private lateinit var localSource: NewsLocalDataSource

    @MockK
    private lateinit var mapper: GetNewsMapper

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        Dispatchers.setMain(Dispatchers.IO)
    }

    @After
    fun finish() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test execute when network result is success`() = runBlocking {

        val successNetworkResult = NetworkResult.Success(
            NewsResponse(
                status = "OK",
                nResults = 1,
                listOf()
            )
        )

        val expectedNewsData = DomainResult.Success(
            NewsData(
                listOf(
                    NewsData.Article(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
            )
        )

        //mock methods calls
        coEvery { dataSource.getNews() } returns flowOf(successNetworkResult)
        coEvery { localSource.saveNews(any()) } returns Unit
        coEvery { mapper.transformNetworkToDb(any()) } returns listOf()
        coEvery { mapper.transformNetworkToUI(any()) } returns expectedNewsData

        val result = useCase.execute()
        result.collect { domainResult ->
            assertEquals(expectedNewsData, domainResult)
        }
        coVerify { localSource.saveNews(any()) } //check if the data is saved into db
        coVerify (exactly = 0){ localSource.getNews() } //check if is not fetching data from bd

    }


    @Test
    fun `test execute when network result is error and local data source returns null`() =
        runBlocking {

            val errorNetworkResult = NetworkResult.Error<NewsResponse>(
                500,
                ""
            )

            val expectedNewsData = DomainResult.Error<NewsData>(
                500,
                ""
            )


            //mock methods calls
            coEvery { dataSource.getNews() } returns flowOf(errorNetworkResult)
            coEvery { localSource.getNews() } returns null
            coEvery { mapper.transformNetworkToUI(any()) } returns expectedNewsData


            val result = useCase.execute()
            result.collect { domainResult ->
                assertEquals(expectedNewsData, domainResult)
            }

            coVerify { localSource.getNews() } //check if is fetching data from bd
            coVerify (exactly = 0){ localSource.saveNews(any()) } //check if the data is not saved into db


        }

    @Test
    fun `test execute when network result is error and local data source returns empty list`() =
        runBlocking {

            val errorNetworkResult = NetworkResult.Error<NewsResponse>(
                500,
                ""
            )

            val expectedNewsData = DomainResult.Error<NewsData>(
                500,
                ""
            )


            //mock methods calls
            coEvery { dataSource.getNews() } returns flowOf(errorNetworkResult)
            coEvery { localSource.getNews() } returns emptyList()
            coEvery { mapper.transformNetworkToUI(any()) } returns expectedNewsData


            val result = useCase.execute()
            result.collect { domainResult ->
                assertEquals(expectedNewsData, domainResult)
            }

            coVerify { localSource.getNews() } //check if is fetching data from bd
            coVerify (exactly = 0){ localSource.saveNews(any()) } //check if the data is not saved into db

        }


    @Test
    fun `test execute when network result is error and local data source returns data list`() =
        runBlocking {

            val errorNetworkResult = NetworkResult.Error<NewsResponse>(
                500,
                ""
            )

            val expectedNewsData = DomainResult.Success(
                NewsData(
                    listOf(
                        NewsData.Article(
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                        )
                    )
                )
            )


            //mock methods calls
            coEvery { dataSource.getNews() } returns flowOf(errorNetworkResult)
            coEvery { localSource.getNews() } returns listOf(
                NewsEntity(
                    id = "1",
                    title = "",
                    resume = "",
                    byline = "",
                    publishedDate = "",
                    thumbnailUrl = ""
                )
            )
            coEvery { mapper.transformDbToUI(any()) } returns expectedNewsData

            val result = useCase.execute()
            result.collect { domainResult ->
                assertEquals(expectedNewsData, domainResult)
            }
        }

}