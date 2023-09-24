package com.example.moviemania.ui.main

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.adapter.StatefulAdapter
import com.example.moviemania.data.repository.MovieRepository
import com.example.moviemania.model.MovieData

import com.example.moviemania.model.PopularMovies
import com.example.moviemania.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val  TAG = "Main View Model"
    private val MutablepopularMovies  = MutableLiveData<List<MovieData>>()
    val popularMovies : LiveData<List<MovieData>> = MutablepopularMovies

    private val data : MutableStateFlow<State<List<MovieData>>> = MutableStateFlow(State.loading())
    val MovieData : StateFlow<State<List<MovieData>>> = data


    fun getPopularMovies(authToken :String)
    {
        //viewModelScope.launch {


            /*try {
                val popularMovies = movieRepository.getPopularMovies(authToken).body()?.result
                MutablepopularMovies.postValue(popularMovies)
                Log.d(TAG,popularMovies.toString())
            }
            catch (e:Exception) {
                Log.d(TAG, e.toString())
            }*/
            //MutablepopularMovies.value = ppopularMovies

            try {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        movieRepository.getMovieData(authToken)
                            .map { Resource -> State.fromResource(Resource) }
                            .collect { state -> data.value = state }
                    }
                    }
                    }
            catch (e :Exception) {
                Log.d(TAG, e.toString())
            }
        }



    fun getPopularTvSeries(authToken: String)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            try {
                val popularTvSeries = movieRepository.getPopularTvSeries(authToken)
            }
            catch (e : Exception)
            {
                Log.d(TAG,"Exception in getPopularTvSeries: $e")
            }
        }
    }
}
