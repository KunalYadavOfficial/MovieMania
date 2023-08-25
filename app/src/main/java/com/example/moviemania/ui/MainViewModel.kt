package com.example.moviemania.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemania.data.repository.MovieRepository

import com.example.moviemania.model.PopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val MutablepopularMovies  = MutableLiveData<PopularMovies>()
    val popularMovies :LiveData<PopularMovies> = MutablepopularMovies

    fun getPopularMovies(authToken :String)
    {
        viewModelScope.launch {
            try {
                val popularMovies = movieRepository.getPopularMovies(authToken)
                //Log.d("Main View Model",popularMovies.body().toString())
            }
            catch (e:Exception) {
                Log.d("Main View Model", e.toString())
            }
            //MutablepopularMovies.value = ppopularMovies
        }
    }
}
