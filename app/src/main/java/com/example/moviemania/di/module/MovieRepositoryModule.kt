package com.example.moviemania.di.module

import com.example.moviemania.data.repository.MovieRepository
import com.example.moviemania.data.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class MovieRepositoryModule {


    @ActivityRetainedScoped
    @Binds
    abstract fun bindPostRepository(repository: MoviesRepository): MovieRepository


}