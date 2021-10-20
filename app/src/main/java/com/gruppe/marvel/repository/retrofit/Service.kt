package com.gruppe.unifique.retrofit

import com.gruppe.marvel.repository.retrofit.model.FilmDto
import retrofit2.Response
import retrofit2.http.GET


interface Service {

    @GET("saga")
    suspend fun getAllFilmes(): Response<List<FilmDto>>

}