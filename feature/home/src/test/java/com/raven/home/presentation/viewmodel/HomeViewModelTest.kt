package com.raven.home.presentation.viewmodel

import app.cash.turbine.test
import com.raven.home.domain.DomainResult
import com.raven.home.domain.usecases.GeNewsUseCase
import com.raven.home.presentation.NewsData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
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
import kotlin.time.ExperimentalTime


@ExperimentalCoroutinesApi
@ExperimentalTime
class HomeViewModelTest {
    @InjectMockKs
    private lateinit var viewModel: HomeViewModel

    @MockK
    private lateinit var useCase: GeNewsUseCase

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
    fun `test getNews when use case returns success`() = runBlocking {
        coEvery {
            useCase.execute()
        } returns flowOf(
            DomainResult.Success(
                NewsData(
                    listOf(
                        NewsData.Article(
                            "",
                            "",
                            "",
                            "",
                            ""
                        )
                    )
                )
            )
        )
        viewModel.articles.test {
            viewModel.getNews()
            TestCase.assertNull(awaitItem()) //initial value
            assertEquals(1, awaitItem()?.size)
            cancelAndIgnoreRemainingEvents()

        }
    }


    @Test
    fun `test getNews when use case returns error`() = runBlocking {
        val myError = "MY_ERROR"
        val myCode = 500
        coEvery {
            useCase.execute()
        } returns flowOf(DomainResult.Error(myCode, myError))

        viewModel.error.test {
            viewModel.getNews()
            TestCase.assertNull(awaitItem()) //initial value
            assertEquals(myError, awaitItem())
            cancelAndIgnoreRemainingEvents()

        }
    }

}