package com.testinggifsproject.features.history

import androidx.lifecycle.ViewModel
import com.testinggifsproject.model.GifModelData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HistoryVM : ViewModel() {

    private val _listHistory = MutableStateFlow<ArrayList<GifModelData>>(arrayListOf())
    val listHistory: StateFlow<ArrayList<GifModelData>> = _listHistory


}