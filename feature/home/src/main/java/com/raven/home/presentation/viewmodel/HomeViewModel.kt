package com.raven.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raven.home.domain.DomainResult
import com.raven.home.domain.usecases.GeNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val useCase: GeNewsUseCase) : ViewModel() {
    fun getNews() {
        viewModelScope.launch {
            useCase.execute().collect {
                when (it) {
                    is DomainResult.Error -> {
                    }

                    is DomainResult.Success -> {
                    }
                }
            }
        }
    }
}


