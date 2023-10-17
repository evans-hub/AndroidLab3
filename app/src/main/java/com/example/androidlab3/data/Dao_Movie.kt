package com.example.androidlab3.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidlab3.Model.Movie

@Dao
interface Dao_Movie {
    @Insert
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM films ORDER BY id ASC")
    fun getAllMovies(): LiveData<List<Movie>>
}
