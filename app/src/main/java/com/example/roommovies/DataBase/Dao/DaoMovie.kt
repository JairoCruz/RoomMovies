package com.example.roommovies.DataBase.Dao

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
    /*
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
    @Query("SELECT * FROM  movie ORDER BY name ASC")
    fun getAllMovies(): List<Movie>


}