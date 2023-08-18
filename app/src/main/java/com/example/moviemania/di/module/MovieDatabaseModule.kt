package com.example.moviemania.di.module

import android.app.Application
import com.example.moviemania.data.source.local.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MovieDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = MovieDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: MovieDatabase) = database.getMovieDao()
}