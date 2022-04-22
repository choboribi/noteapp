package com.example.notesapp

import Adapter.NotesAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.room.Room
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.databinding.ActivityNoteViewBinding
import com.example.notesapp.NoteViewActivity
import com.google.android.material.snackbar.Snackbar
import entities.NoteDao
import entities.Note
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class NoteViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        super.onCreate(savedInstanceState)
        binding = ActivityNoteViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, AppDatabase.name).build()

        var getBody = intent.getStringExtra("body")
        var getId = intent.getLongExtra("note", -1)

        if (getBody != null) {
            binding.editTextTextMultiLine.setText(getBody)
        }

        binding.generateQr.setOnClickListener {

            if (binding.editTextTextMultiLine.text.contentEquals("")) {
                var newSnack = Snackbar.make(binding.baseView, "Can't make QR Code from empty note!", 3)
                newSnack.show()
            }
            else {
                val intent = Intent(this, DisplayQRActivity::class.java)
                intent.putExtra("test", binding.editTextTextMultiLine.text.toString())

                startActivity(intent)
            }

        }

        binding.save.setOnClickListener {
            runOnIO {
                db.noteDao().update(changeNoteBody(db.noteDao().getNote(getId), binding.editTextTextMultiLine.text.toString()))
            }
        }

    }

    fun changeNoteBody(note: Note, body: String): Note {
        var newNote: Note
        newNote = Note(note.title, body, note.parentSetId, note.id)
        return newNote
    }
}