package com.gruppe.marvel.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FilmEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDataBase :RoomDatabase(){
    abstract val filmsDAO : FilmsDAO

    companion object {

        @Volatile
        private var INSTANCE: FilmDataBase? = null

        fun getInstance(context: Context): FilmDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FilmDataBase::class.java,
                        "film_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}