package com.testinggifsproject.di

import com.testinggifsproject.usecases.FindGifByNameUseCase
import com.testinggifsproject.usecases.GetGifByIDUseCase
import com.testinggifsproject.usecases.GetRandomListGifsUseCase
import com.testinggifsproject.usecases.database.ClearHistoryUseCase
import com.testinggifsproject.usecases.database.GetHistoryUseCase
import com.testinggifsproject.usecases.database.RemoveGifByIdUseCase
import org.koin.dsl.module

private val useCasesModule = module {
    factory { GetRandomListGifsUseCase(get()) }
    factory { FindGifByNameUseCase(get()) }
    factory { GetGifByIDUseCase(get()) }
    factory { GetHistoryUseCase(get()) }
    factory { RemoveGifByIdUseCase(get()) }
    factory { ClearHistoryUseCase(get()) }
}

val domainModule = arrayOf(useCasesModule)