package com.example.androidlab3.repository

import androidx.lifecycle.LiveData
import com.example.androidlab3.data.MovieDao
import com.example.androidlab3.model.Movie

class MovieRepository(private val movieDao: MovieDao) {

    val allMovies: LiveData<List<Movie>> = movieDao.getAllMovies()

    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }
}
