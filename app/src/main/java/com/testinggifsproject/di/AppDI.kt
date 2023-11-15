package com.testinggifsproject.di

import com.testinggifsproject.features.main.MainVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelsModule = module {
    viewModel {
        MainVM(get())
    }
}
val appModule = arrayOf(viewModelsModule)