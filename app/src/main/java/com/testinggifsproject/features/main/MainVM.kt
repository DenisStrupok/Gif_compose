package com.testinggifsproject.features.main

import androidx.lifecycle.ViewModel
import com.testinggifsproject.usecases.TestUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainVM(
    private val testUseCase: TestUseCase
) : ViewModel() {

    private val _test = MutableStateFlow("")
    val test: StateFlow<String> = _test


    fun test() {
        _test.value = testUseCase.startTestMethod()
    }

}