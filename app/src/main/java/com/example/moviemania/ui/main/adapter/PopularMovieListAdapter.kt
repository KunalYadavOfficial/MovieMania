package com.example.moviemania.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviemania.R
import com.example.moviemania.model.MovieData
import com.example.moviemania.model.MovieDetails
import com.example.moviemania.model.PopularMovies
import com.example.moviemania.ui.main.viewholder.PopularMovieVH

class PopularMovieListAdapter : ListAdapter<MovieData, PopularMovieVH>(DIFF_CALLBACK) {

    private val TAG = "PopularMovieListAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieVH {

        Log.d(TAG,"onCreateViewHolder called")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_movies_item_view, parent, false)
        return PopularMovieVH(view)
    }


    override fun onBindViewHolder(holder: PopularMovieVH, position: Int) {
        Log.d(TAG,"onBindViewHolder")
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieData>() {
            override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean =
                oldItem == newItem
        }
    }


}