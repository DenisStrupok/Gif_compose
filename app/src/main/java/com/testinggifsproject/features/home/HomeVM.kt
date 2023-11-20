package com.testinggifsproject.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testinggifsproject.model.Gif
import com.testinggifsproject.model.GifsModel
import com.testinggifsproject.usecases.FindGifByNameUseCase
import com.testinggifsproject.usecases.GetRandomListGifsUseCase
import com.testinggifsproject.usecases.ResultCallbacks
import kotlinx.coroutines.flow.MutableStateFlow

class HomeVM(
    private val getRandomListGifsUseCase: GetRandomListGifsUseCase,
    private val findGifByNameUseCase: FindGifByNameUseCase
) : ViewModel() {

    private val _listGifs = MutableStateFlow<GifsModel?>(null)
    val gifs get() = _listGifs

    private val _gifName = MutableStateFlow<String?>(null)
    val gifName get() = _gifName

    init {
        getRandomListGifs()
    }

    private fun getRandomListGifs() {
        getRandomListGifsUseCase.invoke(
            uiDispatcher = viewModelScope,
            ResultCallbacks(
                onSuccess = { gifs ->
                    _listGifs.value = gifs
                }
            ),
            params = GetRandomListGifsUseCase.Params(
                limit = 10,
                offset = 0
            )
        )
    }

    fun setNameGif(name: String) {
        _gifName.value = name
        if ((gifName.value?.length ?: 0) > 2) {
            findGifByNameUseCase.invoke(
                uiDispatcher = viewModelScope,
                ResultCallbacks(
                    onSuccess = {
                        gifs.value = it
                    }
                ),
                params = FindGifByNameUseCase.Params(
                    name = gifName.value ?: "",
                    limit = 10,
                    offset = 0
                )
            )
        }
    }
}