package com.example.moviemania.ui.main


import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemania.R
import com.example.moviemania.data.repository.MovieRepository
import com.example.moviemania.databinding.ActivityMainBinding
import com.example.moviemania.model.State
import com.example.moviemania.ui.main.MainActivity.API.API_TOKEN
import com.example.moviemania.ui.main.adapter.PopularMovieListAdapter
import com.example.moviemania.ui.main.adapter.PopularSeriesListAdapter
import com.example.moviemania.utils.NetworkUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel : MainViewModel
    private val TAG : String = "Main Activity"
    @Inject lateinit var moviesRepository : MovieRepository
    private val mAdapter = PopularMovieListAdapter()
    private val SeriesAdapter = PopularSeriesListAdapter()

    val mViewModel : MainViewModel by viewModels()

    object API{
        const val API_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYzM3ZDM4ZmYwOTY1ZGRmOTE0YTViMTNjMmFjMGMyMCIsInN1YiI6IjY0ODc0ZDQzYzAzNDhiMDBjODJmNGFhZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zm2wGkYgzp5YhIdY8F2J2owppb66BuRnNhLUseVTYBs"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mViewModel.getPopularMovies(API_TOKEN)

        observePosts()

        /*viewModel.popularMovies.observe(this){
            if(it.isNotEmpty())
            {
                mAdapter.submitList(it)
            }
        }*/

        /**
         * Fetching popular movies
         */
        /*lifecycleScope.launch(Dispatchers.IO)
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
    }*/

        /**
         * Fetching Popular TV Series
         */
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val tvSeries = moviesRepository.getPopularTvSeries(API_TOKEN)
                val tsries = tvSeries.body()?.result
                Log.d(TAG, "Tv Series =${tsries.toString()}")
                Log.d(TAG, tsries?.size.toString())
                if (tsries!!.isNotEmpty()) {
                    SeriesAdapter.submitList(tsries)
                }
            }
            catch (e : Exception)
            {
                Log.d(TAG,e.toString())
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

    private fun observePosts() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.MovieData
                    .onCompletion {
                        Log.d(TAG,"Movie Data fetching compoleted")
                    }
                    .collect { state ->
                    when (state) {
                        is State.Loading -> Log.d(TAG,"Loading data")
                        is State.Success -> {
                            if (state.data.isNotEmpty()) {
                                mAdapter.submitList(state.data.toMutableList())
                                //zshowLoading(false)
                            }
                        }
                        is State.Error -> {
                            Log.d(TAG,state.message)
                            //showToast(state.message)
                            //showLoading(false)
                        }
                    }
                }
            }
        }
    }



    override fun onStart() {
        super.onStart()


        val MovieRecyclerView = findViewById<RecyclerView>(R.id.rv_popularMovies)
        val SeriesRecyclerView = findViewById<RecyclerView>(R.id.rv_TVSeries)
        SeriesRecyclerView.setHasFixedSize(true)
        SeriesRecyclerView.adapter = SeriesAdapter


        //val linearLayoutManager = LinearLayoutManager(this)
        //linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        //MovieRecyclerView.layoutManager = linearLayoutManager
        MovieRecyclerView.setHasFixedSize(true)
        MovieRecyclerView.adapter = mAdapter
        //handleNetworkChanges()
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