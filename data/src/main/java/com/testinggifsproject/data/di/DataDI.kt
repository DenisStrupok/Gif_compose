package com.testinggifsproject.data.di

import androidx.room.Room
import com.testinggifsproject.data.database.GifDatabase
import com.testinggifsproject.data.repositories.DatabaseRepositoryImpl
import com.testinggifsproject.data.repositories.GifRepositoryImpl
import com.testinggifsproject.data.service.GifService
import com.testinggifsproject.repositories.DatabaseRepository
import com.testinggifsproject.repositories.GifRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val repositoriesModule = module {
    factory<GifRepository> { GifRepositoryImpl(get(), get()) }
    factory<DatabaseRepository> { DatabaseRepositoryImpl(get()) }
}

private val serviceModule = module {
    single<GifService> {
        (get() as Retrofit).create(GifService::class.java)
    }
}
private val networkModule = module {
    val loggerInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggerInterceptor)
        .build()

    factory {
        Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

private val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = GifDatabase::class.java,
            name = "gif_database"
        ).build()
    }
}

val dataModule = arrayOf(repositoriesModule, serviceModule, networkModule, databaseModule)