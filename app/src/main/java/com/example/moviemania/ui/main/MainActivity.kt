package com.example.moviemania.ui.main


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemania.R
import com.example.moviemania.data.repository.MovieRepository
import com.example.moviemania.ui.main.MainActivity.API.API_TOKEN
import com.example.moviemania.ui.main.adapter.PopularMovieListAdapter
import com.example.moviemania.utils.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel : MainViewModel
    private val TAG : String = "Main Activity"
    @Inject lateinit var moviesRepository : MovieRepository
    private val mAdapter = PopularMovieListAdapter()



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
        /*val mAdapter = PopularMovieListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.rv_popularMovies)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mAdapter*/
        /*val movies = moviesRepository.getPopularMovies(API_TOKEN)
        val pmovies = movies.body()?.result
        if (pmovies!!.isNotEmpty()) {
            mAdapter.submitList(pmovies)
        }*/

        /**
         * Fetching popular movies
         */
        lifecycleScope.launch(Dispatchers.IO)
    {
        try {
            val movies = moviesRepository.getPopularMovies(API_TOKEN)
            val pmovies = movies.body()?.result
            Log.d(TAG, "Pmovies =${pmovies.toString()}")
            Log.d(TAG, pmovies?.size.toString())
            if (pmovies!!.isNotEmpty()) {
                mAdapter.submitList(pmovies)

                //recyclerView.adapter = mAdapter
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }



        //Log.d("Main Activity test",test.body().toString())
        //val movie  =MovieNetwork.movieNetwork.getPopularMovies(API_TOKEN)
        //Log.d("Main Activity API result", movie.toString())


        /**
         * Fetching particular movie detail
         */
      /*  lifecycleScope.launch(Dispatchers.IO) {

            try {
                val movieDetails = moviesRepository.getMovieDetails(API_TOKEN,614479)
                Log.d(TAG,movieDetails.body().toString())

            }
            catch (e : Exception) {
                Log.d(TAG,e.toString())
            }
        }*/
    }


    override fun onStart() {
        super.onStart()


        val recyclerView = findViewById<RecyclerView>(R.id.rv_popularMovies)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mAdapter
        handleNetworkChanges()
    }

    fun handleNetworkChanges()
    {
        NetworkUtil.getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            //No internet connectivity
            if(!isConnected)
            {
                //show a bottom/top network status bar as in youtube app
            }
            else
            {
                //internet connectivity available

            }

        }
    }



}