package com.testinggifsproject.di

import com.testinggifsproject.usecases.FindGifByNameUseCase
import com.testinggifsproject.usecases.GetRandomListGifsUseCase
import org.koin.dsl.module

private val useCasesModule = module {
    factory { GetRandomListGifsUseCase(get()) }
    factory { FindGifByNameUseCase(get()) }
}

val domainModule = arrayOf(useCasesModule)