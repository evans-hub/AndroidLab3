package com.example.androidlab3

import android.content.res.Configuration
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlab3.data.DB_Movie
import com.example.androidlab3.Adapters.Movie_Adapter
import com.example.androidlab3.viewmodel.MVMFactory
import com.example.androidlab3.Model.Movie
import com.example.androidlab3.repository.MovieRepo
import com.example.androidlab3.viewmodel.MViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MViewModel
    private lateinit var movieAdapter: Movie_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setTheme(R.style.Theme_Splash)
        } else {
            setTheme(R.style.Theme_AndroidLab3)
        }

        val dao = DB_Movie.getDatabase(application).movieDao()
        val repository = MovieRepo(dao)
        movieViewModel = ViewModelProvider(this, MVMFactory(repository))
            .get(MViewModel::class.java)

        setupRecyclerView()

        val titleEditText = findViewById<EditText>(R.id.editText1)
        val genreEditText = findViewById<EditText>(R.id.editText2)

        Button.setOnClickListener {
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
        movieAdapter = Movie_Adapter()
        recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        movieViewModel.allMovies.observe(this) { movies ->
            movieAdapter.submitList(movies)
        }
    }
}
