package com.example.roommovies.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommovies.DataBase.Dao.DaoMovie
import com.example.roommovies.DataBase.Entity.Movie

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

        fun getDatabase(context: Context): RDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return  tempInstance
            }

            synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RDatabase::class.java,
                    "MovieDB"
                ).build()

                INSTANCE = instance

                return instance
            }
        }


    }

}