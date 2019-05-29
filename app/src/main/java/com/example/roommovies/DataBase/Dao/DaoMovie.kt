package com.example.roommovies.DataBase.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roommovies.DataBase.Entity.Movie

// Implement the DAO

// TODO: 1 - CREATE DAO(DATA ACCESS OBJECT). DAO MUST BE AN INTERFACE OR ABSTRACT CLASS

// TODO: 2 - ADD ANNOTATION @Dao TO THE INTERFACE OR ABSTRACT CLASS FOR ROOM
@Dao
interface DaoMovie {

    // TODO: 3 - DECLARE A suspension METHOD TO INSERT ONE MOVIE: suspend fun insert(movie: Movie)
    /**
    * By default, all queries must be executed on a separate thread.
    * Room has coroutines support.
    * So your queries can be annotated with the suspend modifier and called from a coroutine or from another suspension function.
    * */
    // TODO: 4 - ANNOTATE THE METHOD WITH @Insert
    @Insert
    suspend fun insert(movie: Movie)


    // TODO: 5 - ADD ANOTHER METHOD CALLED deleteAll() WITH ANNOTATION @Query("delete from movie")
    @Query("DELETE FROM movie")
    fun deleteAll()

    // TODO: 6 - ADD ANOTHER METHOD CALLED getAllMoviews THAN RETURN A List of Movie:  List<Movie>, WITH ANNOTATION @Query("select * from movie")

    // TODO: 7 - ADD LiveData CLASS

    /**
    * 7.1
    * When data changes you usually want to take some action,
    * such as displaying the updated data in the UI.
     * This means you have to observe the data so that when it changes, you can react.
     *
    * */

    /**
    * 7.2
     * If you, the developer, want to update the stored data, you must use MutableLiveData instead of LiveData.
     * The MutableLiveData class has two public methods that allow you to set the value of a LiveData object, setValue(T) and postValue(T).
     * Usually, MutableLiveData is used in the ViewModel, and then the ViewModel only exposes immutable LiveData objects to the observers.
    * */

    /**
     * 7.3
     * LiveData, a lifecycle library class for data observation, solves this problem.
     * Use a return value of type LiveData in your method description, and Room generates all necessary code to update the LiveData when the database is updated.
     * */
    @Query("SELECT * FROM  movie ORDER BY name ASC")
    fun getAllMovies(): LiveData<List<Movie>>


}