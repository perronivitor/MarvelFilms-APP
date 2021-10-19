package com.gruppe.marvel.di

import com.gruppe.marvel.rotines.detail.FilmDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        FilmDetailViewModel()
        //(repository : FilmRepository) -> FilmViewModel(repository)
    }
}