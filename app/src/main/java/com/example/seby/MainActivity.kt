package com.example.seby

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlab3.R
import com.example.seby.Adapters.MovieAdapter
import com.example.seby.Model.Movie
import com.example.seby.repository.MovieRepo
import com.example.seby.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieRepo = MovieRepo()

        movieViewModel = ViewModelProvider(this, MovieViewModel.Factory(movieRepo))
            .get(MovieViewModel::class.java)

        setupRecyclerView()

        val titleEditText = findViewById<EditText>(R.id.editText1)
        val genreEditText = findViewById<EditText>(R.id.editText2)
        val button = findViewById<Button>(R.id.saveButton)

        button.setOnClickListener {
            val title = titleEditText.text.toString()
            val genre = genreEditText.text.toString()

            if (title.isNotBlank() && genre.isNotBlank()) {
                val movie = Movie(movieTitle = title, movieGenre = genre)
                movieViewModel.insert(movie)

                titleEditText.text.clear()
                genreEditText.text.clear()
            }
        }
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter()
        recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        movieViewModel.allMovies.observe(this) { movies ->
            movieAdapter.submitList(movies)
        }
    }
}

