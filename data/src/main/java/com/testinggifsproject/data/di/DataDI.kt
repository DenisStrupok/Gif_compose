package com.testinggifsproject.data.di

import com.testinggifsproject.data.repositories.GifRepositoryImpl
import com.testinggifsproject.data.service.GifService
import com.testinggifsproject.repositories.GifRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val repositoriesModule = module {
    factory<GifRepository> { GifRepositoryImpl(get()) }
}

private val serviceModule = module {
    single<GifService> {
        (get() as Retrofit).create(GifService::class.java)
    }
}
private val networkModule = module {

    factory {
        Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

val dataModule = arrayOf(repositoriesModule, serviceModule, networkModule)