package com.example.notes

import android.app.Application
import com.example.notes.db.NotesDatabase

class NotesApp :Application(){
    override fun onCreate() {
        super.onCreate()

    }
     fun getNoteDb(): NotesDatabase {
       return NotesDatabase.getInstance(this)
 }
}