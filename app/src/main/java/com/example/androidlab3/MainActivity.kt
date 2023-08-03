package com.example.androidlab3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlab3.data.Movie_DB
import com.example.androidlab3.Adapters.Adapter
import com.example.androidlab3.viewmodel.MVMFactory
import com.example.androidlab3.model.Movie
import com.example.androidlab3.repository.MovieRepository
import com.example.androidlab3.viewmodel.MViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MViewModel
    private lateinit var movieAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = Movie_DB.getDatabase(application).movieDao()
        val repository = MovieRepository(dao)
        movieViewModel = ViewModelProvider(this, MVMFactory(repository))
            .get(MViewModel::class.java)

        setupRecyclerView()

        Button.setOnClickListener {
            val titleEditText = findViewById<TextInputLayout>(R.id.editTextTextPersonName).editText
            val title = titleEditText?.text.toString()

            val genreEditText = findViewById<TextInputLayout>(R.id.editTextTextPersonName2).editText
            val genre = genreEditText?.text.toString()

            if (title.isNotBlank() && genre.isNotBlank()) {
                val movie = Movie(title = title, genre = genre)
                movieViewModel.insert(movie)

                titleEditText?.text?.clear()
                genreEditText?.text?.clear()
            }
        }

    }

    private fun setupRecyclerView() {
        movieAdapter = Adapter()
        recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        movieViewModel.allMovies.observe(this) { movies ->
            movieAdapter.submitList(movies)
        }
    }
}
