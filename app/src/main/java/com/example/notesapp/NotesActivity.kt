package com.example.notesapp

import Adapter.NotesAdapter
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.notesapp.databinding.ActivityNotesBinding
import entities.Note
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode

class NotesActivity : AppCompatActivity() {
    private lateinit var inNotes: MutableList<Note>
    private lateinit var binding: ActivityNotesBinding
    private var parentSetId: Long = -1
    val scanQrCode = registerForActivityResult(ScanQRCode(), ::handleResult)


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //get id from folder tapped
        parentSetId = intent.getLongExtra(PARENT_SET_ID_TAG, -1)
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, AppDatabase.name).build()
        //set up list of notes
        binding.notesRecyclerView.adapter = NotesAdapter(parentSetId, db.noteDao())

        binding.addNoteButton.setOnClickListener{
            (binding.notesRecyclerView.adapter as NotesAdapter).addItem(Note("New", "", parentSetId))
                binding.notesRecyclerView.smoothScrollToPosition((binding.notesRecyclerView.adapter as NotesAdapter).itemCount - 1)
        }

        binding.scanqrcode.setOnClickListener{
            scanQrCode.launch(null)
        }


    }
    fun handleResult(result: QRResult) {
        //handle result based on https://github.com/G00fY2/quickie/issues/34
        val textimport = when (result){
            is QRResult.QRSuccess -> result.content.rawValue
            QRResult.QRUserCanceled -> "User canceled"
            QRResult.QRMissingPermission -> "Missing permission"
            is QRResult.QRError -> "${result.exception.javaClass.simpleName}: ${result.exception.localizedMessage}"
        }
        (binding.notesRecyclerView.adapter as NotesAdapter).addItem(Note("Imported Note", textimport.toString(), parentSetId))
        binding.notesRecyclerView.smoothScrollToPosition((binding.notesRecyclerView.adapter as NotesAdapter).itemCount - 1)
    }
}