package com.gruppe.marvel

import android.app.Application
import com.gruppe.marvel.di.repositoryModule
import com.gruppe.marvel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppAplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@AppAplication)
            modules(viewModelModule, repositoryModule)
        }
    }
}