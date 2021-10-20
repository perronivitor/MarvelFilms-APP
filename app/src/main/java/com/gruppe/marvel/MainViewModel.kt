package com.gruppe.marvel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruppe.marvel.presentation.FilmDetail
import com.gruppe.marvel.repository.local.FilmEntity
import com.gruppe.marvel.repository.local.toFilmDetail
import com.gruppe.marvel.repository.retrofit.model.FilmDto
import com.gruppe.marvel.repository.retrofit.model.toFilmDetail
import com.gruppe.unifique.retrofit.model.Resultado
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    var mProgressBar = MutableLiveData(false)
    var error = MutableLiveData<String>()

    private var _allFilms = MutableLiveData<List<FilmDetail>>()
    val mAllFilms get() = _allFilms

    init {
        getAllFilms()
    }

    private fun getAllFilms() {
        viewModelScope.launch {
            try {
                mProgressBar.postValue(true)
                when (val repo = repository.getAllFilmes()) {
                    is Resultado.Sucesso -> {
                        val films = mutableListOf<FilmDetail>()
                        repo.dado?.forEach { f ->
                            films.add(f.toFilmDetail())
                        }
                        _allFilms.postValue(films)
                    }
                    is Resultado.Erro -> {
                        error.postValue(repo.exception.message)
                    }
                }
            } catch (e: Exception) {

            } finally {
                mProgressBar.postValue(false)
            }
        }
    }
}