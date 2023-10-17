package com.example.androidlab3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidlab3.Model.Movie
import com.example.androidlab3.repository.MovieRepo
import kotlinx.coroutines.launch

class MViewModel(private val repository: MovieRepo) : ViewModel() {

    val allMovies: LiveData<List<Movie>> = repository.allMovies

    fun insert(movie: Movie) = viewModelScope.launch {
        repository.insert(movie)
    }
}
