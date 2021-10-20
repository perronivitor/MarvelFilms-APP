package com.gruppe.marvel.di

import com.gruppe.marvel.MainRepository
import com.gruppe.marvel.MainViewModel
import com.gruppe.marvel.repository.local.FilmDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
        viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
        single { MainRepository() }
}