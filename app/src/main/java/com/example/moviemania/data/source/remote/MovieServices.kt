package com.example.moviemania.data.source.remote

import com.example.moviemania.model.MovieDetails
import com.example.moviemania.model.PopularMovies
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


//API endPoints
interface MovieServices {


    //https://api.themoviedb.org/3/movie/popular
    //auth token
    //eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYzM3ZDM4ZmYwOTY1ZGRmOTE0YTViMTNjMmFjMGMyMCIsInN1YiI6IjY0ODc0ZDQzYzAzNDhiMDBjODJmNGFhZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zm2wGkYgzp5YhIdY8F2J2owppb66BuRnNhLUseVTYBs
    @GET("movie/popular?page=1")
    @Headers("accept: application/json")
      suspend fun getPopularMovies(
        @Header("Authorization") authToken : String,
    ) : Response<PopularMovies>


    @GET("movie/{movie_id}")
    @Headers("accept: application/json")
    suspend fun getMovieDetails(
        @Header("Authorization") authToken: String,
        @Path("movie_id") movieId :Int
    ) : Response<MovieDetails>
    //base url : https://api.themoviedb.org/3/
    //https://image.tmdb.org/t/p/w185/fiVW06jE7z9YnO4trhaMEdclSiC.jpg for fetching image

    //Singleton object not lazy
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"

    }


}

//singleton object but lazy
/*
object MovieNetwork
{
    //const val BASE_URL = "https://api.themoviedb.org/3/"
    private val retrofit  = Retrofit.Builder()
        .baseUrl(MovieServices.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
        //.addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieNetwork  =   retrofit.create(MovieServices::class.java)
}*/
