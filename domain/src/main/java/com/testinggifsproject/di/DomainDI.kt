package com.testinggifsproject.di

import com.testinggifsproject.usecases.TestUseCase
import org.koin.dsl.module

private val useCasesModule = module {
    factory { TestUseCase() }
}

val domainModule = arrayOf(useCasesModule)