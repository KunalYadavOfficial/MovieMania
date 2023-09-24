package com.example.moviemania.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemania.model.MovieData
import kotlinx.coroutines.flow.Flow

/**
 *
 */
@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies : List<MovieData>)

   @Query("Select * from ${MovieData.TABLE_MOVIE_DATA}")
    fun getMovies() : Flow<List<MovieData>>
}