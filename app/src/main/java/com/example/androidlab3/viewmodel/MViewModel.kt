package com.example.androidlab3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlab3.model.Movie
import com.example.androidlab3.repository.MovieRepository
import kotlinx.coroutines.launch

class MViewModel(private val repository: MovieRepository) : ViewModel() {

    val allMovies: LiveData<List<Movie>> = repository.allMovies

    fun insert(movie: Movie) = viewModelScope.launch {
        repository.insert(movie)
    }
}
