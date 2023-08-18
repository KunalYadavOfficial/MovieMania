package com.example.moviemania.ui


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviemania.R
import com.example.moviemania.data.repository.MovieRepository

import com.example.moviemania.ui.MainActivity.API.API_TOKEN
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.Response
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel : MainViewModel
    private val TAG : String = "Main Activity"
    @Inject lateinit var moviesRepository : MovieRepository

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
        /*val dbInstance = MovieDatabase.getInstance(applicationContext)
        val moviesRepository  = MoviesRepository(
            MovieNetwork, dbInstance
        )*/
        lifecycleScope.launch(Dispatchers.IO){
            try {
                val movies =  moviesRepository.getPopularMovies(API_TOKEN)
                Log.d(TAG,movies.body().toString())
            }
         catch (e : Exception)
         {
             Log.d(TAG,e.toString())
         }
        }
        //Log.d("Main Activity test",test.body().toString())
        //val movie  =MovieNetwork.movieNetwork.getPopularMovies(API_TOKEN)
        //Log.d("Main Activity API result", movie.toString())



        lifecycleScope.launch(Dispatchers.IO) {

            try {
                val movieDetails = moviesRepository.getMovieDetails(API_TOKEN,614479)
                Log.d(TAG,movieDetails.body().toString())

            }
            catch (e : Exception) {
                Log.d(TAG,e.toString())
            }
        }
    }
}