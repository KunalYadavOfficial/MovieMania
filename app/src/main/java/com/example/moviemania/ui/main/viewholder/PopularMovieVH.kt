package com.example.moviemania.ui.main.viewholder

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.moviemania.R
import com.example.moviemania.model.MovieData
import com.example.moviemania.model.MovieDetails
import com.example.moviemania.model.PopularMovies

class PopularMovieVH(private val view : View) : RecyclerView.ViewHolder(view) {


    fun bind(popularMovies: MovieData)
    {
        val posterURL = "https://image.tmdb.org/t/p/w185/${popularMovies.posterPath}"
        Log.d("Popular Movie VH" , posterURL)
        //http://image.tmdb.org/t/p/w185//8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg
        view.findViewById<ImageView>(R.id.im_popularMovies)
            //.setImageResource(R.drawable.sample_poster)
           .load(posterURL){
               size(400,700)
           }
    }
}




/*
class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post, onItemClicked: (Post, ImageView) -> Unit) {
        binding.postTitle.text = post.title
        binding.postAuthor.text = post.author
        binding.imageView.load(post.imageUrl) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }*/
