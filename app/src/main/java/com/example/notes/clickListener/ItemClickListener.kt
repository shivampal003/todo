package com.example.notes.clickListener

import com.example.notes.db.Notes



interface ItemClickListener {
    fun onClick(notes: Notes)
    fun onUpdate(notes: Notes)
}

