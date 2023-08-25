package com.example.moviemania.data.repository




import com.example.moviemania.data.source.local.database.MovieDatabase
import com.example.moviemania.data.source.remote.MovieServices
import com.example.moviemania.model.MovieDetails
import com.example.moviemania.model.PopularMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Path
import javax.inject.Inject


interface MovieRepository {

    suspend fun getPopularMovies(authToken : String) : Response<PopularMovies>
    suspend fun getMovieDetails(authToken: String,movieId: Int) : Response<MovieDetails>
    //fun getMoviesById(val id : MovieId) :
}

class MoviesRepository @Inject constructor(private val networkService : MovieServices,
                        private val movieDatabase : MovieDatabase
) : MovieRepository
{

    val moviesDao = movieDatabase.getMovieDao()


    //RequestBoundNetwork

    override suspend fun getPopularMovies(authToken: String): Response<PopularMovies>{

        return networkService.getPopularMovies(authToken)
    }

    override suspend fun getMovieDetails(authToken: String,movieId:Int): Response<MovieDetails> {
        return networkService.getMovieDetails(authToken,movieId)
    }

}






