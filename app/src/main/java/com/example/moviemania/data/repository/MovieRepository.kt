package com.example.moviemania.data.repository




import com.example.moviemania.data.source.local.database.MovieDatabase
import com.example.moviemania.data.source.remote.MovieServices
import com.example.moviemania.model.MovieData
import com.example.moviemania.model.MovieDetails
import com.example.moviemania.model.PopularMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Path
import javax.inject.Inject


interface MovieRepository {

     suspend fun getPopularMovies(authToken : String) : Response<PopularMovies>
    suspend fun getMovieDetails(authToken: String,movieId: Int) : Response<MovieDetails>
    //fun getMoviesById(val id : MovieId) :

    suspend fun getPopularTvSeries(authToken: String)  : Response<PopularMovies>

    suspend fun getMovieData(authToken: String) : Flow<Resource<List<MovieData>>>
}

class MoviesRepository @Inject constructor(private val networkService : MovieServices,
                        private val movieDatabase : MovieDatabase
) : MovieRepository
{

    val moviesDao = movieDatabase.getMovieDao()


    //NetworkBoundRepository
    override suspend fun getMovieData(authToken: String): Flow<Resource<List<MovieData>>> {


            return object : NetworkBoundRepository<PopularMovies, List<MovieData>>() {
                override fun fetchFromLocal(): Flow<List<MovieData>> {

                    return moviesDao.getMovies()
                }

                override suspend fun fetchRemoteData(): Response<PopularMovies> {
                    return networkService.getPopularMovies(authToken)
                }

                override suspend fun saveRemoteData(response: Response<PopularMovies>) {
                    val data = response.body()?.result
                    if (data != null) {
                        moviesDao.addMovies(data)
                    }
                }
            }.asFlow()
            //return networkService.getPopularMovies(authToken)

    }

    override suspend fun getPopularMovies(authToken: String): Response<PopularMovies>{
        return networkService.getPopularMovies(authToken)
    }

    override suspend fun getMovieDetails(authToken: String,movieId:Int): Response<MovieDetails> {
        return networkService.getMovieDetails(authToken,movieId)
    }

    override suspend fun getPopularTvSeries(authToken: String): Response<PopularMovies> {
        return networkService.getPopularTVSeries(authToken)
    }

}






