package com.example.roommovies.Ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roommovies.DataBase.Entity.Movie
import com.example.roommovies.R

/**
 *
 * Add a RecyclerView
 * to display the data in a RecyclerView, which is a little nicer than just throwing the data in a TextView.
 * This codelab assumes that you know how RecyclerView, RecyclerView.LayoutManager, RecyclerView.ViewHolder, and RecyclerView.Adapter work.
 *
 * */

// TODO: 1 -  CREATE CLASS MovieListAdapter THAT EXTENDS RecyclerView.Adapter<ViewHolder>()
class MovieListAdapter internal constructor(context: Context) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    // TODO: 3 - DECLARE A value TYPE LayoutInflater
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    // TODO: 4 - DECLARE A variable TYPE List<Moview> EMPTY
    // cache copy of movie
    private var movie = emptyList<Movie>()

    // TODO: 5 - IMPLEMENT METHOD FROM RecyclerView.Adapter. onCreateViewHolder, getItemCount and onBindViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = inflater.inflate(R.layout.recycleview_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return movie.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val current = movie[position]
        holder.moviewItemView.text = current.name
    }

    // TODO: 2 - ADD MovieViewHolder LIKE A inner class
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // Declare a val type TextView and use findViewById
       val moviewItemView: TextView = itemView.findViewById(R.id.textViewMovie)
   }

}