package com.testinggifsproject.di

import com.testinggifsproject.features.detail.DetailVM
import com.testinggifsproject.features.history.HistoryVM
import com.testinggifsproject.features.home.HomeVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelsModule = module {
    viewModel {
        HomeVM(get(), get())
    }
    viewModel {
        DetailVM(get())
    }
    viewModel {
        HistoryVM(get(), get(), get())
    }
}
val appModule = arrayOf(viewModelsModule)