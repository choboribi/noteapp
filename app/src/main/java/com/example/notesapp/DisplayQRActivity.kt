package com.example.notesapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import com.example.notesapp.databinding.ActivityDisplayQrBinding
import com.example.notesapp.databinding.ActivityNoteViewBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class DisplayQRActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayQrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_display_qr)

        binding = ActivityDisplayQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var testString = intent.getStringExtra("test")
//        println(testString)
//        println("another test")
        // reference code for generating qr code
        // https://code.luasoftware.com/tutorials/android/android-generate-qrcode-with-xzing/
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(testString, BarcodeFormat.QR_CODE, 512, 512)

        binding.imageView.setImageBitmap(bitmap)

        binding.saveQr.setOnClickListener {
            var titleTime = System.currentTimeMillis().toString()
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, titleTime, "QR Code");
        }
    }
}