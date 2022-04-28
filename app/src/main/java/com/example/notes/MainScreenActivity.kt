package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.Note
import com.example.notes.data.NoteRVAdapter
import com.example.notes.data.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainScreenActivity : AppCompatActivity(), NoteRVAdapter.NoteClickInterface,
    NoteRVAdapter.NoteClickDeleteInterface {

    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        supportActionBar?.hide()

        notesRV = findViewById(R.id.idRVNotes)
        addFAB = findViewById(R.id.idFABAddNote)
        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter: NoteRVAdapter = NoteRVAdapter(this, this, this)

        notesRV.adapter = noteRVAdapter
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel:: class.java)
        viewModel.allNotes.observe(this,
            { list -> list?.let { noteRVAdapter.updateList(it) } })


        addFAB.setOnClickListener{
            val intent = Intent(this@MainScreenActivity, AddEditNoteActivity:: class.java)
            startActivity(intent)
            this.finish()

        }
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainScreenActivity, AddEditNoteActivity:: class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteBody", note.noteBody)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()

    }

}