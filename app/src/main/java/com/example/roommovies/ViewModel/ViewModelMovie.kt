package com.example.roommovies.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roommovies.DataBase.Entity.Movie
import com.example.roommovies.DataBase.RDatabase
import com.example.roommovies.Repository.RepositoryMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
 * Implement the ViewModel
 *
 *
 * The ViewModel's role is to provide data to the UI and survive configuration changes.
 * A ViewModel acts as a communication center between the Repository and the UI.
 * You can also use a ViewModel to share data between fragments.
 * The ViewModel is part of the lifecycle library.
 *
 * */

// TODO: 1 - CREATE A class CALLED ViewModelMovie THAT GETS THE Appliction AS A PARAMETER AND EXTENDS AndroidViewModel

class ViewModelMovie(application: Application) : AndroidViewModel(application) {

    // TODO: 2 - ADD A PRIVATE MEMBER VARIABLE TO HOLD A REFERENCE TO THE REPOSITORY
    private val repositoryMovie: RepositoryMovie

    // TODO: 3 - ADD A PRIVATE LiveData MEMBER VARIABLE TO CACHE THE LIST OF MOVIE
    private val allMovies: LiveData<List<Movie>>


    // TODO: 4 - CREATE A init BLOC THAT GETS A REFERENCE TO THE daoMovie FROM THE RDatabase AND CONSTRUCTS THE repositoryMovie BASED ON IT.
    init {

        val movieDao = RDatabase.getDatabase(application).daoMovie()

        repositoryMovie = RepositoryMovie(movieDao)

        // TODO: 5 - IN THE init BLOCK, INITIALIZE THE allMovies PROPERTY WITH THE DATA FROM repositoryMovie
        allMovies = repositoryMovie.allMovies
    }


    // TODO: 6 - CREATE A WRAPPER insert() METHOD THAT CALLS THE REPOSITORYS insert() METHOD.
    /***
     * In this way, the implementation of insert() is completely hidden from the UI.
     * We want the insert() method to be called away from the main thread, so we're launching a new coroutine, based on the coroutine scope defined previously.
     * Because we're doing a database operation, we're using the IO Dispatcher.
     *
     */

    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repositoryMovie.insert(movie)
    }

}