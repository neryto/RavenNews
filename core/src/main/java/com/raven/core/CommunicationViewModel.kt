package com.raven.core

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CommunicationViewModel
    @Inject constructor()
    : ViewModel() {

    private val _navigateToDetail : MutableStateFlow<String?> = MutableStateFlow(null)
    val navigateToDetail : StateFlow<String?> get() = _navigateToDetail

    fun navigateToDetail(id:String){
        _navigateToDetail.value = id
    }

    fun resetFlowStates(){
        _navigateToDetail.value = null
    }

}