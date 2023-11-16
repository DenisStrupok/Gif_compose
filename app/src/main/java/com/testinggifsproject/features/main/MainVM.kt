package com.testinggifsproject.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testinggifsproject.usecases.GetRandomListGifsUseCase
import com.testinggifsproject.usecases.ResultCallbacks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainVM(
    private val getRandomListGifsUseCase: GetRandomListGifsUseCase
) : ViewModel() {

    private val _size = MutableStateFlow(0)
    val size: StateFlow<Int> = _size

    fun getRandomGifsList() {
        getRandomListGifsUseCase.invoke(
            uiDispatcher = viewModelScope,
            ResultCallbacks(
                onSuccess = { gifs ->
                    _size.value = gifs.data?.size ?: 0
                }
            ),
            params = GetRandomListGifsUseCase.Params(
                limit = 10,
                offset = 0
            )
        )
    }

}