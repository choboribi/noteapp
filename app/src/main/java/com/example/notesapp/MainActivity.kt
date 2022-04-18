package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.PARENT_SET_ID_TAG


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var parentSetId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        parentSetId = intent.getLongExtra(PARENT_SET_ID_TAG, -1)

        binding.createFolderButton.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            intent.putExtra(PARENT_SET_ID_TAG, parentSetId)
            startActivity(intent)
        }

    }
}