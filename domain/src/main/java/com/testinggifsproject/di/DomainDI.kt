package com.testinggifsproject.di

import com.testinggifsproject.usecases.GetRandomListGifsUseCase
import com.testinggifsproject.usecases.TestUseCase
import org.koin.dsl.module

private val useCasesModule = module {
    factory { GetRandomListGifsUseCase(get()) }
}

val domainModule = arrayOf(useCasesModule)