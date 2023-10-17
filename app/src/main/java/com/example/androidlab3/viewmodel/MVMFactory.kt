package com.example.androidlab3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidlab3.repository.MovieRepo

class MVMFactory(private val repository: MovieRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}
