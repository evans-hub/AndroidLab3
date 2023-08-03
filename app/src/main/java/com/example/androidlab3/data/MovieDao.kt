package com.example.androidlab3.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidlab3.model.Movie

@Dao
interface MovieDao {
    @Insert
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>
}
