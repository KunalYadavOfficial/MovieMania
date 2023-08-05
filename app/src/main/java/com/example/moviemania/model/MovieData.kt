package com.example.moviemania.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = MovieData.TABLE_MOVIE_DETAILS)
@JsonClass(generateAdapter = true)
data class MovieData(

    @PrimaryKey  val id: Int ?= null,
    @Json(name="original_language")  val originalLanguage: String? =null,
    @Json(name="original_title")  val title:String?=null,
    @Json(name="poster_path")  val posterPath: String? =null,
    @Json(name="vote_average") val rating : String?=null
)
{
    companion object{
        const val TABLE_MOVIE_DETAILS = "MovieDetials"
    }
}

