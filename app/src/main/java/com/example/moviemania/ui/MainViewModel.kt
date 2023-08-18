package com.example.moviemania.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.moviemania.model.PopularMovies
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel () : ViewModel() {

    private val MutablepopularMovies  = MutableLiveData<PopularMovies>()
    val popularMovies :LiveData<PopularMovies> = MutablepopularMovies


    //private val moviesRepository  = MoviesRepository(MovieNetwork, MovieDatabase.getInstance(Application()))

    fun getPopularMovies(authToken :String)
    {
        viewModelScope.launch {
            try {
                //val popularMovies = moviesRepository.getPopularMovies(authToken)
                //Log.d("Main View Model",popularMovies.body().toString())
            }
            catch (e:Exception) {
                Log.d("Main View Model", e.toString())
            }
            //MutablepopularMovies.value = ppopularMovies
        }
    }
}