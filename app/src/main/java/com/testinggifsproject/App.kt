package com.testinggifsproject

import android.app.Application
import com.testinggifsproject.data.di.dataModule
import com.testinggifsproject.di.appModule
import com.testinggifsproject.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                *appModule,
                *domainModule,
                *dataModule
            )
        }
    }
}