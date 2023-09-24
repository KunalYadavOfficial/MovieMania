package com.example.moviemania.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass



@JsonClass(generateAdapter = true)
data class TvSeriesData(

    //@PrimaryKey val id: Int ?= null,
    @Json(name = "id") val id : Int? = null,
    @Json(name="original_language")  val originalLanguage: String? =null,
    @Json(name="original_title")  val title:String?=null,
    @Json(name="poster_path")  val posterPath: String? =null,
    @Json(name="vote_average") val rating : String?=null
)








