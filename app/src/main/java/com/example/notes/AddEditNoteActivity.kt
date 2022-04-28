package com.example.notes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.notes.data.Note
import com.example.notes.data.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var noteTitleEdt : EditText
    lateinit var noteBodyEdt : EditText
    lateinit var addupdateBtn : Button
    lateinit var viewModel : NoteViewModel
    var noteID = -1;

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        supportActionBar?.hide()

        noteTitleEdt = findViewById(R.id.idEdtNoteTitle)
        noteBodyEdt = findViewById(R.id.idEdtNoteBody)
        addupdateBtn = findViewById(R.id.idBtnAddUpdate)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).
        get(NoteViewModel:: class.java)

        val noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteBody = intent.getStringExtra("noteBody")
            noteID = intent.getIntExtra("noteID",-1)
            addupdateBtn.setText("Update Note")
            noteTitleEdt.setText(noteTitle)
            noteBodyEdt.setText(noteBody)
        }else{
            addupdateBtn.setText("Save Note")
        }
        addupdateBtn.setOnClickListener {
            val noteTitle = noteTitleEdt.text.toString()
            val noteBody = noteBodyEdt.text.toString()

            if(noteType.equals("Edit"))
            {if(noteTitle.isNotEmpty() && noteBody.isNotEmpty())
            {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDate: String = sdf.format(Date())
                val updateNote = Note(noteTitle,noteBody,currentDate )
                updateNote.id = noteID
                viewModel.updateNote(updateNote)
                Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
            }
            }else{
                if(noteTitle.isNotEmpty() && noteBody.isNotEmpty())
                {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle,noteBody,currentDate))
                    Toast.makeText(this, "Note Added..", Toast.LENGTH_LONG).show()
                }
            }
            val intent = Intent(applicationContext, MainScreenActivity:: class.java)
            startActivity(intent)
            this.finish()
        }

     }
}