package com.example.notes.Activity

import Appconstant
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Adapter.NotesAdapter
import com.example.notes.db.Notes
import com.example.notes.NotesApp
import com.example.notes.R
import com.example.notes.Utills.PrefConstant
import com.example.notes.clickListener.ItemClickListener
import com.example.notes.db.NotesDao
import com.example.notes.db.NotesDatabase

import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyNotesActivity : AppCompatActivity() {
    var fullName: String=""
    lateinit var fabutton: FloatingActionButton
    lateinit var sharedPreferences: SharedPreferences
    lateinit var recyclerView: RecyclerView
    var listNotes = ArrayList<Notes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        bindview()
        setupSharedPreferences()
        getIntentData()
        setupToolbarText()
        supportActionBar?.title = fullName
        fabutton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setupDialogBox()
            }

        })


    }

    private fun setupToolbarText() {
        if (supportActionBar!=null){
            supportActionBar?.title=fullName

        }
    }

    private fun setupDialogBox() {
        val view = LayoutInflater.from(this@MyNotesActivity)
            .inflate(R.layout.add_notes_dialog_layout, null)
        val ettittle = view.findViewById<EditText>(R.id.ettittle)
        val etdescription = view.findViewById<EditText>(R.id.etdescription)
        val btnsubmit = view.findViewById<Button>(R.id.btnsubmit)
        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
        btnsubmit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val Tittle = ettittle.text.toString()
                val Description = etdescription.text.toString()
                if (Tittle.isNotEmpty() && Description.isNotEmpty()) {
                    val notes = Notes(tittle=Tittle, description =  Description)
                    listNotes.add(notes)
                    addNotesToDb(notes)
                } else {
                    Toast.makeText(
                        this@MyNotesActivity,
                        "Tittle or Description can't empty",
                        Toast.LENGTH_LONG
                    ).show()
                }

                setupRecyclerView()
                dialog.hide()
            }


        })
        dialog.show()
    }

    private fun addNotesToDb(notes: Notes) {
        val notesApp=applicationContext as NotesApp
        val notesDao=notesApp.getNoteDb().notesDao()
        notesDao.insert( notes)
    }


    private fun setupRecyclerView() {
        val itemClickListener = object : ItemClickListener{
            override fun onClick(notes: Notes) {
                val intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(Appconstant.TITTLE, notes.tittle)
                intent.putExtra(Appconstant.DESCRIPTION, notes.description)
                startActivity(intent)
            }

            override fun onUpdate(notes: Notes) {

            }

        }
        val notesAdapter = NotesAdapter(listNotes, itemClickListener)
        val linearLayoutManager = LinearLayoutManager(this@MyNotesActivity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = notesAdapter

    }
    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    }




        private fun getIntentData() {
            val intent = intent
            fullName = intent.getStringExtra(Appconstant.FULL_NAME).toString()
            if (fullName.isEmpty()) {
                fullName = sharedPreferences.getString(PrefConstant.FULL_NAME, "").toString()
            }
        }


        private fun bindview() {
            fabutton = findViewById(R.id.fabutton)
            recyclerView = findViewById(R.id.recyclerViewNotes)

        }

}