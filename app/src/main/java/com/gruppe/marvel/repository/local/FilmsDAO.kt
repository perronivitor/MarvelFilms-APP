package com.gruppe.marvel.repository.local

import androidx.room.*


@Dao
interface FilmsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save (film : List<FilmEntity>)

    @Query("SELECT * FROM film_table")
    fun getAllFilms(): List<FilmEntity>

}