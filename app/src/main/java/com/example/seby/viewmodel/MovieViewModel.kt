package com.example.seby.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seby.Model.Movie
import com.example.seby.repository.MovieRepo
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider

class MovieViewModel(private val repository: MovieRepo) : ViewModel() {

    val allMovies = repository.allMovies

    fun insert(movie: Movie) {
        viewModelScope.launch {
            repository.insert(movie)
        }
    }
    class Factory(private val repository: MovieRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
