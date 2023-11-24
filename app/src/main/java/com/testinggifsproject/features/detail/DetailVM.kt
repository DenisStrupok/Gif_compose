package com.testinggifsproject.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testinggifsproject.model.GifModelData
import com.testinggifsproject.usecases.GetGifByIDUseCase
import com.testinggifsproject.usecases.ResultCallbacks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailVM(
    private val getGifByIDUseCase: GetGifByIDUseCase
) : ViewModel() {

    private val _gifId = MutableStateFlow("")
    private var _gif = MutableStateFlow<GifModelData?>(null)
    val gif: StateFlow<GifModelData?> = _gif
    fun setGifId(value: String) {
        _gifId.value = value
        getGif()
    }

    private fun getGif() {
        if (_gifId.value.isNotBlank()) {
            getGifByIDUseCase.invoke(
                uiDispatcher = viewModelScope,
                params = GetGifByIDUseCase.Params(gifId = _gifId.value),
                result = ResultCallbacks(
                    onSuccess = { gif ->
                        this._gif.value = gif
                    },
                )
            )
        }
    }
}