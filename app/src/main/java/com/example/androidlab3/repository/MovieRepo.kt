package com.example.androidlab3.repository

import androidx.lifecycle.LiveData
import com.example.androidlab3.data.Dao_Movie
import com.example.androidlab3.Model.Movie

class MovieRepo(private val movieDaoMovie: Dao_Movie) {

    val allMovies: LiveData<List<Movie>> = movieDaoMovie.getAllMovies()

    suspend fun insert(movie: Movie) {
        movieDaoMovie.insert(movie)
    }
}
