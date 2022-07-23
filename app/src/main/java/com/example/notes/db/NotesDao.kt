package com.example.notes.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE


@Dao
interface NotesDao{

    @Query("select * from notesData")
    fun getAll():List<Notes>

    @Insert(onConflict = REPLACE)
    fun insert(notes: Notes)

    @Update
    fun update(notes: Notes)

    @Delete
    fun delete(notes: Notes)
}