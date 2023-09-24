package com.example.moviemania.di.module

import com.example.moviemania.data.source.remote.MovieServices
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class MovieApiModule {


    //this binding has a singleton scope // scoped to singleton component

    @Singleton
    @Provides
    fun ProvideMovieApi() : MovieServices = Retrofit.Builder()
            .baseUrl(MovieServices.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(
                        KotlinJsonAdapterFactory()
                    ).build()))
            //.addConverterFactory(GsonConverterFactory.create())
            .build()
        .create(MovieServices::class.java)

}

