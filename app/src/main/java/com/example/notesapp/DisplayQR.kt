package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.databinding.ActivityDisplayQrBinding
import com.example.notesapp.databinding.ActivityNoteViewBinding

class DisplayQR : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayQrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_display_qr)

        binding = ActivityDisplayQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var testString = intent.getStringExtra("test")
        binding.textView.text=testString
    }
}