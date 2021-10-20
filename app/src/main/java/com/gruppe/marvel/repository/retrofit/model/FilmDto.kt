package com.gruppe.marvel.repository.retrofit.model

import com.gruppe.marvel.presentation.FilmDetail

data class FilmDto(
    val actors: String,
    val director: String,
    val genre: String,
    val plot: String,
    val poster: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val title: String,
    val writer: String,
    val year: String
)

fun FilmDto.toFilmDetail(): FilmDetail {
    return FilmDetail(
        img = poster,
        runtime = runtime,
        title = title,
        year = year,
        rated = rated,
        plot = plot,
        director = director.split(","),
        genre = genre.split(",")
    )
}
