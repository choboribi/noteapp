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
    private val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, AppDatabase.name).build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getBody = intent.getStringExtra("body")

        if (getBody != null) {
            binding.editTextTextMultiLine.setText(getBody)
        }

        binding.generateQr.setOnClickListener {

            val intent = Intent(this, DisplayQRActivity::class.java)
            println(binding.editTextTextMultiLine.text)
            intent.putExtra("test",binding.editTextTextMultiLine.text.toString())

            startActivity(intent)

        }

        binding.save.setOnClickListener {

            runOnIO {
                var tempNote = db.noteDao().getNote(intent.getLongExtra("note", -1))
                tempNote.body = binding.editTextTextMultiLine.text.toString()
                db.noteDao().update(tempNote)
            }

            var snackbar: Snackbar
            snackbar = Snackbar.make(binding.root, "Saving...", 1)

        }
    }

    override fun onStop() {
        super.onStop()

        runOnIO {
            var tempNote = db.noteDao().getNote(intent.getLongExtra("note", -1))
            tempNote.body = binding.editTextTextMultiLine.text.toString()
            db.noteDao().update(tempNote)
        }
/*        fun save() = runBlocking {
            launch {
                var currentNote = db.noteDao().getNote(intent.getLongExtra("note", -1))

            }
        }*/


    }
}