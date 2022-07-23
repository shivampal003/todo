package com.example.notes.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.notes.R

class DetailActivity : AppCompatActivity() {
    lateinit var textViewtittle:TextView
    lateinit var textViewDescription:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindview()
        setupIntentData()
    }

    private fun setupIntentData() {
        val intent=intent
        val Tittle=intent.getStringExtra(Appconstant.TITTLE)
        val Description=intent.getStringExtra(Appconstant.DESCRIPTION)
        textViewtittle.text=Tittle
        textViewDescription.text=Description
    }

    private fun bindview() {
        textViewtittle=findViewById(R.id.textViewtittle)
        textViewDescription=findViewById(R.id.textViewdescription)
    }
}