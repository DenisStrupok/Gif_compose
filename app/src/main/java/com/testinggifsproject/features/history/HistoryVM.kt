package com.testinggifsproject.features.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testinggifsproject.model.GifModelData
import com.testinggifsproject.model.GifTesModel
import com.testinggifsproject.usecases.ResultCallbacks
import com.testinggifsproject.usecases.database.GetHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HistoryVM(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {

    private val _listHistory = MutableStateFlow<List<GifTesModel>>(listOf())
    val listHistory: StateFlow<List<GifTesModel>> = _listHistory


    fun getListHistory() {
        getHistoryUseCase.invoke(
            uiDispatcher = viewModelScope,
            result = ResultCallbacks(
                onSuccess = { list ->
                    _listHistory.value = list
                }
            ),
            params = null
        )
    }
}