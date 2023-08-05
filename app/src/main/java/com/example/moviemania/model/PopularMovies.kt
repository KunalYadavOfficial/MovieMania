package com.example.moviemania.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



@Entity(tableName = PopularMovies.TABLE_POPULAR_MOVIE)
@JsonClass(generateAdapter = true)
data class PopularMovies(

    @Json(name="page")  val pages :Int? = null,
    @Json(name="results")  var result : List<MovieData>,
    @Json(name="total_pages")  val totalPages : Int?=null,
    @Json(name="total_results")  val totalResults : Int? =null
)
{
    companion object{
        const val TABLE_POPULAR_MOVIE = "PopularMovie"
    }
}