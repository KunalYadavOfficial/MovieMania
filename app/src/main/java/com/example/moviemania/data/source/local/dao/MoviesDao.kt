package com.example.moviemania.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemania.model.MovieData


@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies : List<MovieData>)

   @Query("Select * from MovieDetials")
    suspend fun getMovies() : List<MovieData>
}