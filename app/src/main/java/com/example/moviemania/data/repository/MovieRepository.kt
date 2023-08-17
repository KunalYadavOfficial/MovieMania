package com.example.moviemania.data.repository




import com.example.moviemania.data.source.local.database.MovieDatabase
import com.example.moviemania.data.source.remote.MovieNetwork
import com.example.moviemania.data.source.remote.MovieNetwork.movieNetwork
import com.example.moviemania.data.source.remote.MovieServices
import com.example.moviemania.model.MovieDetails
import com.example.moviemania.model.PopularMovies
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Path


interface MovieRepository {

    suspend fun getPopularMovies(authToken : String) : Response<PopularMovies>
    suspend fun getMovieDetails(authToken: String,movieId: Int) : Response<MovieDetails>
    //fun getMoviesById(val id : MovieId) :
}


class MoviesRepository (private val networkService : MovieNetwork,
                        private val movieDatabase : MovieDatabase
) : MovieRepository
{

    val moviesDao = movieDatabase.getMovieDao()


    //RequestBoundNetwork

    override suspend fun getPopularMovies(authToken: String): Response<PopularMovies>{

        return networkService.movieNetwork.getPopularMovies(authToken)
    }

    override suspend fun getMovieDetails(authToken: String,movieId:Int): Response<MovieDetails> {
        return networkService.movieNetwork.getMovieDetails(authToken,movieId)
    }

}






