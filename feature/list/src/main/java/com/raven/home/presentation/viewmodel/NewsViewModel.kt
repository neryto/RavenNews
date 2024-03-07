package com.raven.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raven.home.domain.DomainResult
import com.raven.home.domain.usecases.GeNewsUseCase
import com.raven.home.presentation.NewsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(private val useCase: GeNewsUseCase) : ViewModel() {
    private val _error : MutableStateFlow<String?> = MutableStateFlow(null)
    val error : StateFlow<String?> get() = _error

    private val _articles : MutableStateFlow<List<NewsData.Article>?> = MutableStateFlow(null)
    val articles : StateFlow<List<NewsData.Article>?> get() = _articles


    fun getNews() {
        viewModelScope.launch {
            useCase.execute().collect {result->
                when (result) {
                    is DomainResult.Error -> _error.value = result.message
                    is DomainResult.Success -> _articles.value = result.data.articles
                }
            }
        }
    }
}


