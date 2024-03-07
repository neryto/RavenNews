package com.raven.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raven.detail.domain.NewData
import com.raven.detail.domain.usecases.GetNewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(private val getNewUseCase: GetNewUseCase) : ViewModel() {

    private val _newData: MutableStateFlow<NewData?> = MutableStateFlow(null)
    val newData: StateFlow<NewData?> get() = _newData

    fun getNews(id: String) {
        viewModelScope.launch {
            getNewUseCase.execute(id).collect { newData ->
                _newData.value = newData
            }
        }
    }
}