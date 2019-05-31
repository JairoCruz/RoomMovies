package com.example.roommovies.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roommovies.DataBase.Dao.DaoMovie
import com.example.roommovies.DataBase.Entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Implement the Room Database
 * */

// TODO: 1 - CREATE A public abstract CLASS THANT EXTENDS RoomDatabase AND CALL IT.

// TODO: 2 - ANNOTATE THE CLASS TO BE A Room database, declare the entities belogn in the database and set the version number
@Database(entities = [Movie::class], version = 1)
abstract class RDatabase : RoomDatabase() {

    // TODO: 3 - DEFINE THE DAOs THAT WORK WITH THE DATABASE
    abstract fun daoMovie(): DaoMovie

    // TODO: 4 - MAKE THE RoomDatabase A singleton TO PREVENT HAVING MULTIPLE INSTANCES OF THE DATABASE OPENED AT THE SAME TIME.

    // pattern singleton

    // In kotlin used companion object{} to make a method static
    companion object {
        /**
         * Marks the JVM backing field of the annotated property as volatile, meaning that writes to this field are immediately made visible to other threads.
         * */
        @Volatile
        private var INSTANCE: RDatabase? = null

        // TODO: 5 - ADD PARAMETER scope: CoroutineScope
        fun getDatabase(context: Context, scope: CoroutineScope): RDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return  tempInstance
            }

            // TODO: 7 - ADD CALLBACK MovieDatabaseCallback() TO THE DATABASE BUILD SEQUENCE RIGHT BEFORE CALLING .build()
            synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RDatabase::class.java,
                    "MovieDB"
                ).addCallback(MovieDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                return instance
            }
        }


    }


    // TODO: 6 - CREATE A CUSTOM IMPLEMENTATION OF THE RoomDatabase.Callback().
    /**
     * That also gets a CoroutineScope as constructor parameter.
     * Then , you override the onOpen method to populate the database.
     * This callback is only populate database to initialize
     * */
    private class  MovieDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {
                database -> scope.launch(Dispatchers.IO) {
                populateDatabase(database.daoMovie())
            }
            }
        }

        // I use suspend Coroutine
        suspend fun populateDatabase(daoMovie: DaoMovie) {
            daoMovie.deleteAll()

            var movie = Movie("Spider Man")
            daoMovie.insert(movie)
            movie = Movie("Matrix")
            daoMovie.insert(movie)

        }

    }


}