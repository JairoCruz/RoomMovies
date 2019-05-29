package com.example.roommovies.DataBase.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO: 1 - CREATE THE DATA CLASS

// TODO: 2 - ADD ANNOTATION @Entity(tableName ="")

/*
*
* Annotations for member variable
* */

// TODO: 3 -  ADD ANNOTATION @PrimaryKey, EVERY ENTITY NEEDS A PRIMARY KEY

// TODO: 4 - ADD ANNOTATION  @ColumnInfo, SPECIFY THE NAME OF THE COLUMN IN THE TABLE

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String
)