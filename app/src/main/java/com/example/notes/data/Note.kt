package com.example.notes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text
import java.text.DateFormat

@Entity(tableName = "note_table")

 class Note (@ColumnInfo(name = "title") val noteTitle: String,
             @ColumnInfo(name = "body") val noteBody: String,
             @ColumnInfo(name = "datestamp") val dateStamp: String
) {
     @PrimaryKey(autoGenerate = true)
     var id = 0
}