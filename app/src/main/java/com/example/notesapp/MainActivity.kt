package com.example.notesapp

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.PARENT_SET_ID_TAG
import com.example.notesapp.adapter.FolderAdapter
import entities.Note
import entities.NotesFolders
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var parentSetId: Long = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, AppDatabase.name).build()
        binding.notesFolderTitle.adapter = FolderAdapter(db.folderDao())

        parentSetId = intent.getLongExtra(PARENT_SET_ID_TAG, -1)

        binding.createFolderButton.setOnClickListener {
            (binding.notesFolderTitle.adapter as FolderAdapter).addItem(NotesFolders("New"))
            binding.notesFolderTitle.smoothScrollToPosition((binding.notesFolderTitle.adapter as FolderAdapter).itemCount - 1)
        }

    }
}