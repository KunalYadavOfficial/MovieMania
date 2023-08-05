package com.example.moviemania.ui


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviemania.R
import com.example.moviemania.data.repository.MoviesRepository
import com.example.moviemania.data.source.local.database.MovieDatabase
import com.example.moviemania.data.source.remote.MovieNetwork
import com.example.moviemania.data.source.remote.MovieServices
import com.example.moviemania.model.PopularMovies
import com.example.moviemania.ui.MainActivity.API.API_TOKEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.Response
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel : MainViewModel

    object API{

        const val API_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYzM3ZDM4ZmYwOTY1ZGRmOTE0YTViMTNjMmFjMGMyMCIsInN1YiI6IjY0ODc0ZDQzYzAzNDhiMDBjODJmNGFhZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zm2wGkYgzp5YhIdY8F2J2owppb66BuRnNhLUseVTYBs"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*movieViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        lifecycleScope.launch {
            movieViewModel.getPopularMovies(api.API_TOKEN)
        }
        movieViewModel.popularMovies*/
        val dbInstance = MovieDatabase.getInstance(applicationContext)
        val moviesRepository  = MoviesRepository(
            MovieNetwork, dbInstance
        )
        lifecycleScope.launch(Dispatchers.IO){
            try {
                val movies =  moviesRepository.getPopularMovies(API_TOKEN)
                Log.d("Main Activity test",movies.body().toString())
            }
         catch (e : Exception)
         {
             Log.d("Main Activity",e.toString())
         }
        }
        //Log.d("Main Activity test",test.body().toString())
        //val movie  =MovieNetwork.movieNetwork.getPopularMovies(API_TOKEN)
        //Log.d("Main Activity API result", movie.toString())



        lifecycleScope.launch(Dispatchers.IO) {

            try {
                val movieDetails = moviesRepository.getMO
            }
        }
    }
}