package com.example.moviemania.di.module

import com.example.moviemania.data.repository.MovieRepository
import com.example.moviemania.data.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class MovieRepositoryModule {



    @ActivityRetainedScoped
    @Binds
    abstract fun bindMovieRepository(repository: MoviesRepository): MovieRepository



}