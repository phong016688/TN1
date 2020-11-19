package com.datn.mobileapp

import android.app.Application
import com.datn.mobileapp.di.repositoryModule
import com.datn.mobileapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(viewModelModule, repositoryModule)
            printLogger(Level.DEBUG)
        }
    }
}
