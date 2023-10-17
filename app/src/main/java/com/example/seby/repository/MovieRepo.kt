package com.example.seby.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.seby.Model.Movie

class MovieRepo {

    private val _allMovies = MutableLiveData<List<Movie>>(emptyList())
    val allMovies: LiveData<List<Movie>>
        get() = _allMovies

    fun insert(movie: Movie) {
        val updatedList = _allMovies.value.orEmpty().toMutableList()
        updatedList.add(movie)
        _allMovies.value = updatedList
    }
}


