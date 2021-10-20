package com.gruppe.marvel.presentation

import android.os.Parcelable
import com.gruppe.marvel.repository.local.FilmEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmDetail(
    val img: String,
    val title: String,
    val runtime: String,
    val year: String,
    val rated: String,
    val plot: String,
    val genre: List<String>,
    val director: List<String>,
) : Parcelable

fun FilmDetail.toFilmEntity(): FilmEntity {
    return FilmEntity(
        img = img,
        title = title,
        runtime = runtime,
        year = year,
        rated = rated,
        plot = plot,
        genre = genre,
        director = director
    )
}