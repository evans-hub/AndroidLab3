package com.example.androidlab3.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val movieTitle: String,
    val movieGenre: String
)
