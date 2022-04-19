package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.databinding.ActivityNoteViewBinding


class NoteView : AppCompatActivity() {
    private lateinit var binding: ActivityNoteViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_note_view)
        binding = ActivityNoteViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateQr.setOnClickListener {

            val intent = Intent(this, DisplayQR::class.java)
            println(binding.editTextTextMultiLine.text)
            intent.putExtra("test",binding.editTextTextMultiLine.text.toString())

            startActivity(intent)

        }
    }
}