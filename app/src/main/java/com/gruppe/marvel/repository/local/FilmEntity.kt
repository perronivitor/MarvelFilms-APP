package com.gruppe.marvel.repository.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gruppe.marvel.presentation.FilmDetail

@Entity(tableName = "film_table")
data class FilmEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val img: String,
    val title: String,
    val runtime: String,
    val year: String,
    val rated: String,
    val plot: String,
    val genre: List<String>,
    val director: List<String>,
)

fun FilmEntity.toFilmDetail() : FilmDetail{
    return FilmDetail(
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