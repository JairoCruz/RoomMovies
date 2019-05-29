package com.example.roommovies.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.roommovies.DataBase.Dao.DaoMovie
import com.example.roommovies.DataBase.Entity.Movie

/**
 * Why use a Repository?
 * A Repository manages queries and allows you to use multiple backends.
 * In the most common example, the Repository implements the logic for deciding whether to fetch data from a network or use results cached in a local database.
 * */

//TODO: 1 - CREATE A public class CALLED RepositoryMovie

// TODO: 2 - DECLARE THE DAO AS A private PROPERTY IN THE CONSTRUCTOR
class RepositoryMovie(private val daoMovie: DaoMovie) {

    // TODO: 3 - Add the list of movies (List<Movie>) as a public property and initialize it. Room executes all queries on a separate thread. Observed LiveData will notify the observer when the data has changed.
    val allMovies: LiveData<List<Movie>> = daoMovie.getAllMovies()

    // TODO: 4 - ADD A WRAPPER FOR THE insert() METHOD.
    /**
     * You must call this on a non-UI thread or your app will crash.
     * Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
     * Add the @WorkerThread annotation, to mark that this method needs to be called from a non-UI thread.
     * Add the suspend modifier to tell the compiler that this needs to be called from a coroutine or another suspending function.
     * */
    @WorkerThread
    suspend fun insert(movie: Movie) {
        daoMovie.insert(movie)
    }

}