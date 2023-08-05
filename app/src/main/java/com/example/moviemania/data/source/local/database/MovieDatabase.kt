package com.example.moviemania.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviemania.data.source.local.dao.MoviesDao
import com.example.moviemania.model.MovieData


@Database(
    entities = [MovieData::class] ,
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    //abstract class
    //abstract method returning dao
    //list the entities
    abstract fun getMovieDao() : MoviesDao



    companion object {

        const val DB_NAME = "movie_database"

        @Volatile
        private var INSTANCE : MovieDatabase? = null

        fun getInstance(context: Context) : MovieDatabase {

            val tempInstance = INSTANCE
            if(tempInstance!=null) {
                return tempInstance
            }

            //initialize the db
            //exoensive operation
            //synchronized block - thread safe
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return INSTANCE as MovieDatabase
            }

        }
    }
}