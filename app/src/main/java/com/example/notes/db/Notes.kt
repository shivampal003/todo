package com.example.notes.db
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="notesData")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,

    @ColumnInfo(name = "tittle")
    var tittle:String="",

    @ColumnInfo(name = "description")
    var description:String="",

    @ColumnInfo(name = "imagePath")
    var imagePath:String="",

    @ColumnInfo(name = "isTaskCompleted")
    var isTaskCompleted:Boolean=false

)