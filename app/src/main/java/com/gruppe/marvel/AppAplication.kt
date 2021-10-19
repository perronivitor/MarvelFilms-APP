package com.gruppe.marvel

import android.app.Application
import com.gruppe.marvel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppAplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppAplication)
            modules(viewModelModule)
        }
    }
}